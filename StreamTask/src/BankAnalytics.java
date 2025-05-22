import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class BankAnalytics {
    // Threshold for reportable transactions in Euro
    private static final double REPORTING_THRESHOLD = 10000.0;
    
    // Threshold percentage for detecting suspicious transactions (just below the threshold)
    private static final double SUSPICIOUS_PERCENT = 0.95;

    List<BankTransfer> findSuspiciousTransactions(List<BankTransfer> transfers) {
        double suspiciousThreshold = REPORTING_THRESHOLD * SUSPICIOUS_PERCENT;
        
        return transfers.stream()
                .filter(t -> t.amount() >= suspiciousThreshold && t.amount() < REPORTING_THRESHOLD)
                .collect(Collectors.toList());
    }

    public List<MonthlyStatistics> calculateMonthlyStatistics(List<BankTransfer> transfers) {
        return transfers.stream()
                .collect(Collectors.groupingBy(
                        t -> YearMonth.from(t.date()),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                monthlyTransfers -> {
                                    double totalAmount = monthlyTransfers.stream()
                                            .mapToDouble(BankTransfer::amount)
                                            .sum();
                                    
                                    double average = monthlyTransfers.stream()
                                            .mapToDouble(BankTransfer::amount)
                                            .average()
                                            .orElse(0);
                                    
                                    return new MonthlyStatistics(
                                            YearMonth.from(monthlyTransfers.getFirst().date()),
                                            totalAmount,
                                            average,
                                            monthlyTransfers.size()
                                    );
                                }
                        )
                ))
                .values()
                .stream()
                .sorted(Comparator.comparing(MonthlyStatistics::month))
                .collect(Collectors.toList());
    }

    public List<CustomerTransactionStatistics> findMaxTransactionsPerCustomer(List<BankTransfer> transfers) {
        // Collects all unique customers (senders and receivers)
        Set<String> allCustomers = Stream.concat(
                transfers.stream().map(BankTransfer::sender),
                transfers.stream().map(BankTransfer::receiver)
        ).collect(Collectors.toSet());

        return allCustomers.stream()
                .map(customer -> {
                    // Find the largest incoming transfer (where customer = receiver)
                    double maxIncoming = transfers.stream()
                            .filter(t -> t.receiver().equals(customer))
                            .mapToDouble(BankTransfer::amount)
                            .max()
                            .orElse(0.0);

                    // Find the largest outgoing transfer (where customer = sender)
                    double maxOutgoing = transfers.stream()
                            .filter(t -> t.sender().equals(customer))
                            .mapToDouble(BankTransfer::amount)
                            .max()
                            .orElse(0.0);

                    return new CustomerTransactionStatistics(customer, maxIncoming, maxOutgoing);
                })
                .sorted(Comparator.comparing(CustomerTransactionStatistics::customer))
                .collect(Collectors.toList());
    }

    public Set<String> findPaymentConnections(List<BankTransfer> transfers, String customer) {
        // Direct connections: All customers to whom the given customer has directly transferred money
        Set<String> directConnections = transfers.stream()
                .filter(t -> t.sender().equals(customer))
                .map(BankTransfer::receiver)
                .collect(Collectors.toSet());

        // Indirect connections: All customers to whom a direct receiver has transferred money
        Set<String> indirectConnections = directConnections.stream()
                .flatMap(directReceiver -> transfers.stream()
                        .filter(t -> t.sender().equals(directReceiver))
                        .map(BankTransfer::receiver))
                .collect(Collectors.toSet());

        // Combine both sets and remove the customer himself
        return Stream.concat(directConnections.stream(), indirectConnections.stream())
                .filter(Predicate.not(customer::equals)) // Removes self-transfers
                .collect(Collectors.toSet());
    }
}
