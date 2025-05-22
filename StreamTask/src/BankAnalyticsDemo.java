import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class BankAnalyticsDemo {
    public static void main(String[] args) {
        List<BankTransfer> transfers = createTestData();
        BankAnalytics analytics = new BankAnalytics();

        System.out.println("===== Suspicious Transactions =====");
        List<BankTransfer> suspiciousTransactions = analytics.findSuspiciousTransactions(transfers);
        suspiciousTransactions.forEach(System.out::println);
        System.out.println();

        System.out.println("===== Monthly Statistics =====");
        List<MonthlyStatistics> monthlyStatistics = analytics.calculateMonthlyStatistics(transfers);
        monthlyStatistics.forEach(stats -> {
            System.out.printf("Month: %s, Total Amount: %.2f€, Average: %.2f€, Count: %d%n",
                    stats.month().format(DateTimeFormatter.ofPattern("MM.yyyy")),
                    stats.totalAmount(),
                    stats.average(),
                    stats.transactionCount());
        });
        System.out.println();

        System.out.println("===== Maximum Transactions per Customer =====");
        List<CustomerTransactionStatistics> customerStatistics = analytics.findMaxTransactionsPerCustomer(transfers);
        customerStatistics.forEach(stat -> {
            System.out.printf("Customer: %s, Max. Incoming: %.2f€, Max. Outgoing: %.2f€%n",
                    stat.customer(),
                    stat.maxIncoming(),
                    stat.maxOutgoing());
        });
        System.out.println();

        String testCustomer = "Alice";
        System.out.printf("===== Payment Connections for Customer: %s =====%n", testCustomer);
        Set<String> connections = analytics.findPaymentConnections(transfers, testCustomer);
        System.out.printf("Direct and indirect payment connections from %s: %s%n", testCustomer, connections);
    }

    private static List<BankTransfer> createTestData() {
        return Arrays.asList(
                // Normal transfers
                new BankTransfer("Alice", "Bob", 1500.00, LocalDate.of(2023, 1, 15)),
                new BankTransfer("Bob", "Charlie", 750.50, LocalDate.of(2023, 1, 20)),
                new BankTransfer("Charlie", "David", 2000.00, LocalDate.of(2023, 2, 5)),
                new BankTransfer("David", "Alice", 500.00, LocalDate.of(2023, 2, 10)),
                new BankTransfer("Eva", "Alice", 3500.00, LocalDate.of(2023, 2, 28)),
                new BankTransfer("Alice", "Eva", 4200.00, LocalDate.of(2023, 3, 15)),
                new BankTransfer("Bob", "Alice", 1200.00, LocalDate.of(2023, 3, 22)),
                new BankTransfer("Charlie", "Eva", 800.00, LocalDate.of(2023, 4, 5)),
                
                // Suspicious transfers (just below 10,000€)
                new BankTransfer("Frank", "Alice", 9750.00, LocalDate.of(2023, 4, 12)),
                new BankTransfer("Eva", "David", 9800.00, LocalDate.of(2023, 4, 20)),
                new BankTransfer("Alice", "Frank", 9850.00, LocalDate.of(2023, 5, 3)),
                
                // Large transfers
                new BankTransfer("David", "Frank", 15000.00, LocalDate.of(2023, 5, 15)),
                new BankTransfer("Frank", "Charlie", 25000.00, LocalDate.of(2023, 5, 28)),
                
                // Additional transfers for monthly grouping
                new BankTransfer("Alice", "Bob", 500.00, LocalDate.of(2023, 6, 12)),
                new BankTransfer("Bob", "Charlie", 600.00, LocalDate.of(2023, 6, 15)),
                new BankTransfer("Charlie", "David", 700.00, LocalDate.of(2023, 6, 18)),
                new BankTransfer("David", "Eva", 800.00, LocalDate.of(2023, 6, 21)),
                new BankTransfer("Eva", "Frank", 900.00, LocalDate.of(2023, 6, 24))
        );
    }
}
