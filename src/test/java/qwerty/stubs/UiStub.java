package qwerty.stubs;

import qwerty.ui.Ui;

/**
 * This class encapsulates the Ui stub for testing purposes, because the real Ui class
 * requires the GUI to be properly started to be used.
 */
public class UiStub extends Ui {
    public void showQwertyMessage(String message) {
        System.out.println("\n" + message);
    }

    public void showError(String message) {
        System.out.println("\nWell done! An error has occurred:\n" + message);
    }
}
