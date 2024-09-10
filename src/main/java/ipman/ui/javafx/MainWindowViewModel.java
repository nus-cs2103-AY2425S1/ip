package ipman.ui.javafx;

import ipman.IpMan;
import ipman.ui.Ui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainWindowViewModel {
    private final IpMan ipMan;
    private final ObservableList<Message> messages = FXCollections.observableArrayList();

    public MainWindowViewModel() {
        this.ipMan = new IpMan(new Ui() {
            @Override
            public void showWelcome() {
                addMessage("Hello! I'm Ip Man.");
                addMessage("What can I do for you?");
            }

            @Override
            public void showMessage(String message) {
                addMessage(message);
            }

            @Override
            public void showMessageF(String message, Object... args) {
                showMessage(String.format(message, args));
            }

            @Override
            public void showError(String message) {
                addMessage(message);
            }

            @Override
            public void showGoodbye() {
                addMessage("That's enough for today. See you another time.");
            }
        });
    }

    private void addMessage(String message) {
        this.messages.add(new Message(MessageAuthor.SYSTEM, message));
    }

    /**
     * Processes the message. Parsing it and executing it
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
