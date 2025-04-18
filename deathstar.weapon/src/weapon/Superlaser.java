package weapon;

import core.ControlCenter;

public class Superlaser {

    public Superlaser() {
        String command = ControlCenter.getCommand();
        if (command.equals("Fire Superlaser")) {
            fireSuperlaser();
        }
    }

    private void fireSuperlaser() {
        System.out.println("Superlaser is firing!");
    }

}
