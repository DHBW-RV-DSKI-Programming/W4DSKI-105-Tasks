import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class FileExplorerApp {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Verzeichnis-Analyse-Tool ===");

        // Ask for the directory path
        System.out.print("Bitte geben Sie einen Verzeichnispfad ein: ");
        String directoryPath = scanner.nextLine();

        try {
            // Create and run the file explorer
            FileExplorer explorer = new FileExplorer(directoryPath);
            explorer.analyze();

            // Display the analysis report
            System.out.println("\n" + explorer.generateReport());

            // Save the report to a file
            explorer.saveReportToFile();
            System.out.println("Der Bericht wurde in 'analysis_result.txt' gespeichert.");

            // File search functionality
            boolean continueSearching = true;
            while (continueSearching) {
                System.out.print("\nMöchten Sie nach Dateien suchen? (j/n): ");
                String answer = scanner.nextLine().trim().toLowerCase();

                if (answer.equals("j")) {
                    System.out.print("Geben Sie einen Suchbegriff ein: ");
                    String searchTerm = scanner.nextLine();

                    List<File> foundFiles = explorer.findFiles(searchTerm);
                    System.out.println("\nGefundene Dateien (" + foundFiles.size() + "):");

                    for (File file : foundFiles) {
                        System.out.println(" - " + file.getAbsolutePath() + " (" + 
                                          String.format("%.2f", file.length() / 1024.0) + " KB)");
                    }
                } else {
                    continueSearching = false;
                }
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Fehler: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ein Fehler ist beim Verarbeiten der Dateien aufgetreten: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("\nProgramm beendet.");
        }
    }
}
