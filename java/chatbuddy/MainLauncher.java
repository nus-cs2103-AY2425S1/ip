package chatbuddy;

public class MainLauncher {
    public static void main(String[] args) {
        String osName = System.getProperty("os.name").toLowerCase();
        String javafxPlatform = null;

        if (osName.contains("win")) {
            javafxPlatform = "win";
        } else if (osName.contains("mac")) {
            javafxPlatform = "mac";
        } else if (osName.contains("linux") || osName.contains("nix") || osName.contains("nux")) {
            javafxPlatform = "linux";
        } else {
            System.err.println("Unsupported OS: " + osName);
            System.exit(1);
        }

        // Set the system property to load the correct JavaFX native libraries
        System.setProperty("javafx.platform", javafxPlatform);

        // Launch the JavaFX application
        Main.main(args);
    }
}
