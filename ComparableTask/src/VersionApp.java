import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VersionApp {
    
    public static void main(String[] args) {
        ArrayList<Version> versions = new ArrayList<>(List.of(
                new Version("2"),
                new Version("1.0.0"),
                new Version("1.1.0"),
                new Version("1.1.1"),
                new Version("2.0.0"),
                new Version("2.1.0"),
                new Version("2.10.1"),
                new Version("2.0.11"),
                new Version("3.0.0"),
                new Version("3.0.1"),
                new Version("3.1.0"),
                new Version("4.0.0"),
                new Version("4.2.1"),
                new Version("4.10.1")
        ));
        Collections.sort(versions);
        for (Version version : versions) {
            System.out.println(version);
        }

    }
    
}
