package ipman.ui;

import ipman.commands.Command;
import ipman.ui.javafx.MainWindow;
import javafx.application.Application;

/**
 * JavaFx UI for chat bot
 */
public class JavaFxUi implements Ui {
    @Override
    public void showWelcome() {

    }

    @Override
    public Command readMessage() {
        return null;
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessageF(String message, Object... args) {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showGoodbye() {

    }
}
