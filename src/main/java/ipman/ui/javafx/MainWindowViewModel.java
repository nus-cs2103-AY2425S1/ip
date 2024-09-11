package ipman.ui.javafx;

import ipman.IpMan;
import ipman.ui.Ui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents the view model for the MainWindow. Holds the state required for the
 * MainWindow and processes new events from the window to transition states.
 */
public class MainWindowViewModel {
    private final IpMan ipMan;
    private final ObservableList<Message> messages = FXCollections.observableArrayList();

    /**
     * Constructs the MainWindowViewModel
     */
    public MainWindowViewModel() {
        this.ipMan = new IpMan(new Ui() {
            @Override
            public void showMessage(String message) {
                addMessage(message);
            }

            @Override
            public void showMessageFormat(String message, Object... args) {
                showMessage(String.format(message, args));
            }

            @Override
            public void showError(String message) {
                addMessage(message);
            }
        });

    }

    public void showWelcome() {
        this.addMessage("Hello! I'm Ip Man.\nWhat can I do for you?");
    }

    private void addMessage(String message) {
        assert !message.isBlank();
        this.messages.add(new Message(MessageAuthor.SYSTEM, message));
    }

    /**
     * Processes the message. Parsing it and executing it
     *
     * @param message message to process
     * @return whether to exit the program
     */
    public boolean processMessage(String message) {
        this.messages.add(new Message(MessageAuthor.USER, message));
        return this.ipMan.executeMessage(message);
    }

    public ObservableList<Message> getMessages() {
        return this.messages;
    }
}
