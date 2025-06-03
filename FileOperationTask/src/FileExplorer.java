import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileExplorer {

    private final Path directoryPath;
    private int totalFiles = 0;
    private int totalDirectories = 0;
    private File largestFile = null;
    private File smallestFile = null;
    private long totalSize = 0;
    private final Map<String, Integer> fileTypeCount = new HashMap<>();
    private final PriorityQueue<File> newestFiles = new PriorityQueue<>(
            5, Comparator.comparing(File::lastModified).reversed());

    public FileExplorer(String directoryPath) {
        this.directoryPath = Paths.get(directoryPath);
    }

    public void analyze() throws IOException {
        File directory = directoryPath.toFile();
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("Ungültiger Verzeichnispfad: " + directoryPath);
        }

        // Start recursively analyzing the directory
        analyzeDirectory(directory);
    }

    private void analyzeDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                totalDirectories++;
                analyzeDirectory(file); // Recursively analyze subdirectories
            } else if (file.isFile()) {
                totalFiles++;
                long fileSize = file.length();
                totalSize += fileSize;

                // Track the largest file
                if (largestFile == null || fileSize > largestFile.length()) {
                    largestFile = file;
                }

                // Track the smallest file
                if (smallestFile == null || fileSize < smallestFile.length()) {
                    smallestFile = file;
                }

                // Count file types
                String extension = getFileExtension(file.getName());
                fileTypeCount.put(extension, fileTypeCount.getOrDefault(extension, 0) + 1);

                // Track the newest files
                newestFiles.offer(file);
                if (newestFiles.size() > 5) {
                    newestFiles.poll();
                }
            }
        }
    }

    public List<File> findFiles(String searchTerm) {
        List<File> foundFiles = new ArrayList<>();
        findFilesRecursive(directoryPath.toFile(), searchTerm.toLowerCase(), foundFiles);
        return foundFiles;
    }

    private void findFilesRecursive(File directory, String searchTerm, List<File> foundFiles) {
        File[] files = directory.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                findFilesRecursive(file, searchTerm, foundFiles);
            } else if (file.getName().toLowerCase().contains(searchTerm)) {
                foundFiles.add(file);
            }
        }
    }

    private String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        return lastDotIndex == -1 ? "No Extension" : fileName.substring(lastDotIndex + 1);
    }

    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== Verzeichnis-Analyse-Bericht ===\n");
        report.append("Analysiertes Verzeichnis: ").append(directoryPath).append("\n\n");

        report.append("Gesamtzahl der Dateien: ").append(totalFiles).append("\n");
        report.append("Gesamtzahl der Unterverzeichnisse: ").append(totalDirectories).append("\n\n");

        report.append("Größte Datei: ");
        if (largestFile != null) {
            report.append(largestFile.getName())
                  .append(" (")
                  .append(String.format("%.2f", largestFile.length() / 1024.0))
                  .append(" KB)\n");
        } else {
            report.append("Keine Dateien gefunden\n");
        }

        report.append("Kleinste Datei: ");
        if (smallestFile != null) {
            report.append(smallestFile.getName())
                  .append(" (")
                  .append(String.format("%.2f", smallestFile.length() / 1024.0))
                  .append(" KB)\n");
        } else {
            report.append("Keine Dateien gefunden\n");
        }

        if (totalFiles > 0) {
            double averageSize = totalSize / (double) totalFiles / 1024.0;
            report.append("Durchschnittliche Dateigröße: ")
                  .append(String.format("%.2f", averageSize))
                  .append(" KB\n\n");
        }

        report.append("Anzahl der Dateien pro Dateityp:\n");
        fileTypeCount.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> report.append(" - ")
                        .append(entry.getKey())
                        .append(": ")
                        .append(entry.getValue())
                        .append("\n"));
        report.append("\n");

        report.append("Die 5 neuesten Dateien:\n");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        List<File> sortedNewestFiles = new ArrayList<>(newestFiles);
        sortedNewestFiles.sort(Comparator.comparing(File::lastModified).reversed());

        for (File file : sortedNewestFiles) {
            report.append(" - ")
                  .append(file.getName())
                  .append(" (")
                  .append(dateFormat.format(new Date(file.lastModified())))
                  .append(")\n");
        }

        return report.toString();
    }

    public void saveReportToFile() throws IOException {
        String report = generateReport();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("analysis_result.txt"))) {
            writer.write(report);
        }
    }
}
