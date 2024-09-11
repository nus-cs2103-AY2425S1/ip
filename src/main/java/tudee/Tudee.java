package tudee;

import tudee.command.Command;
import tudee.parser.Parser;
import tudee.storage.Storage;
import tudee.task.TaskList;
import tudee.ui.Ui;

/**
* The Tudee class represents a chatbot that manages tasks.
* It initializes necessary components such as storage, task list, and UI.
* Through these components, it provides a main loop to interact with the user through commands.
*/
public class Tudee {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Initializes the Tudee chatbot with the specified file path.
     *
     * @param filePath Path to the file where tasks are stored.
     */
    public Tudee(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            taskList = new TaskList(storage.load());
        } catch (tudee.TudeeException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Creates the Tudee chatbot main loop
     */
    public void run() {
        ui.showWelcome();
        boolean isFinished = false;

        while (!isFinished) {
            try {
                String command = ui.readCommand();
                Command c = Parser.parse(command);
                c.execute(taskList, ui, storage);
                isFinished = c.isFinished();
            } catch (tudee.TudeeException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(taskList, ui, storage);
        } catch (TudeeException | AssertionError e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Starts the Tudee chatbot
     */
    public static void main(String[] args) {
        new Tudee("./data/tudee.txt").run();
    }
}
