package mittens.ui;

import java.io.InputStream;
import java.util.List;

import mittens.MittensException;
import mittens.ui.fx.MainWindow;

public class JavaFxUi extends Ui {
    private final MainWindow mainWindow;

    public JavaFxUi(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void showGreetingMessage() {
        mainWindow.printMittensMessage(List.of("Hi, I'm Mittens!", "I'm a cat! Meow :3"));
    }

    @Override
    public void showGoodbyeMessage() {
        mainWindow.printMittensMessage(List.of("Bye-bye! :3"));
    }

    @Override
    public void showRegularMessage(List<String> messages) {
        mainWindow.printRegularMessage(messages);
    }

    @Override
    public void showMittensMessage(List<String> messages) {
        mainWindow.printMittensMessage(messages);
    }

    @Override
    public void showErrorMessage(MittensException e) {
        mainWindow.printErrorMessage(e);
    }

    @Override
    public String getUserInput() {
        return null;
    }
}
