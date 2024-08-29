package serenity;
import java.io.IOException;

/**
 * Represents a chatbot used for task management.
 */

public class Serenity {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Serenity object.
     *
     * @param filePath The path of file where data is stored.
     */

    public Serenity(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (SerenityException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            ui.showMessage(e.getMessage());
        }
    }

    /**
     * Runs the Serenity chatbot
     */

    public void run() {

        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.readCommand();
                Parser.parse(input, tasks, ui, storage);
                isExit = Parser.isExit(input);
            } catch (SerenityException | IOException e) {
                ui.showMessage(e.getMessage());
            }
        }
    }

    /**
     * The main method that runs the Serenity chatbot.
     *
     * @param args
     */

    public static void main(String[] args) {
        new Serenity("data/serenity.txt").run();
    }

}
