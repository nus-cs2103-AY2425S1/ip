package Jard;

import javafx.application.Application;

/**
 * This is the launcher for the Jard application.
 * Code reused from here https://github.com/se-edu/addressbook-level3/commit/12bb91903e71ea1109e04f7369c2169f1c7be39a#diff-98387c2ff1fbaaec6ffe77e368d2a454b07def0f8a7f2d118b8a258cc82a5f4eR13
 *
 * It serves as a workaround to prevent JavaFX runtime component errors in JDK 11+.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
