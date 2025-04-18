package core;

import java.util.ArrayList;

public final class ControlCenter {

    private static final ArrayList<String> commands = new ArrayList<>();

    public static void sendCommand(String command) {
        System.out.println("Sending command: " + command);
        commands.add(command);
    }

    public static String getCommand() {
        String lastCommand = commands.getLast();
        System.out.println("Getting command: " + lastCommand);
        commands.remove(lastCommand);
        return lastCommand;
    }

}
