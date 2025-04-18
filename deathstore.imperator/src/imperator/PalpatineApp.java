package imperator;

import core.ControlCenter;
import defense.ShieldGenerator;
import weapon.Superlaser;

public class PalpatineApp {
    public static void main(String[] args) {
        ControlCenter.sendCommand("Build Shield");
        ShieldGenerator shieldGenerator = new ShieldGenerator();

        System.out.println("---");

        ControlCenter.sendCommand("Fire Superlaser");
        Superlaser superlaser = new Superlaser();
    }
}