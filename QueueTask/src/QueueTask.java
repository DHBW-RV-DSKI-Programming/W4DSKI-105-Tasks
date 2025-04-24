import java.util.ArrayDeque;

public class QueueTask {

    public static void main(String[] args) {
        CommandHistory commandHistory = new CommandHistory(10);

        commandHistory.addCommand("ls -la");
        commandHistory.addCommand("cd /home");
        commandHistory.addCommand("mkdir test");
        commandHistory.addCommand("git status");
        commandHistory.addCommand("git commit -m 'Initial commit'");

        commandHistory.showHistory();

        System.out.println("\nAdding more commands...");

        commandHistory.addCommand("docker ps");
        commandHistory.addCommand("npm install");
        commandHistory.addCommand("gradle build");
        commandHistory.addCommand("java -jar app.jar");
        commandHistory.addCommand("cat file.txt");
        commandHistory.addCommand("grep 'error' logs.txt");  // This should push out the oldest command

        commandHistory.showHistory();
    }

    static class CommandHistory {
        private final int capacity;
        private final ArrayDeque<String> history;

        public CommandHistory(int capacity) {
            this.capacity = capacity;
            this.history = new ArrayDeque<>(capacity);
        }

        public void addCommand(String command) {
            if (history.size() >= capacity) {
                history.removeLast();
            }
            history.addFirst(command);
        }

        public void showHistory() {
            if (history.isEmpty()) {
                System.out.println("No commands in history.");
                return;
            }

            System.out.println("Command History (most recent first):");
            int count = 1;
            for (String command : history) {
                System.out.println(count + ". " + command);
                count++;
            }
        }
    }
}
