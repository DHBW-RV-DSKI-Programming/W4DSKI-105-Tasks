import java.time.LocalDate;

record BankTransfer(String sender, String receiver, double amount, LocalDate date) {
}
