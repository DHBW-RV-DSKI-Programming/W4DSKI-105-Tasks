package defense;

import core.ControlCenter;

public class ShieldGenerator {

    public ShieldGenerator() {
        String command = ControlCenter.getCommand();
        if (command.equals("Build Shield")) {
            startingShields();
        }
    }

    private void startingShields() {
        System.out.println("Shields are starting!");
    }

}
