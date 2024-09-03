package com.nimbus;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Ui.class, "Nimbus", "/images/DaUser.png", "/images/DaDuke.png");
    }
}
