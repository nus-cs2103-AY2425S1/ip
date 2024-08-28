import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Ui {
    private final BufferedReader reader;

    public Ui() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readCommand() throws BobException {
        try {
            return reader.readLine().trim();
        } catch (IOException e) {
            throw new BobException("An error occurred while reading input: " + e.getMessage());
        }
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Bob the bot!\nHow can I help you?");
    }

    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showLoadingSuccess() {
        System.out.println("Saved tasks has been successfully loaded.");
    }

    public void showGoodbye() {
        System.out.println("Bye! Hope to see you again :)");
    }

    public void showNoTasks() {
        System.out.println("There are 0 tasks in your list now. Start adding them!");
    }
}
