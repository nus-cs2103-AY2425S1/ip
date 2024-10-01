package alisa;

import alisa.command.Command;
import alisa.exception.AlisaException;
import alisa.task.TaskList;
import javafx.application.Platform;

public class Alisa {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Creates an instance of Alisa.
     *
     * @param filePath Directory path of the storage file.
     */
    public Alisa(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (AlisaException e) {
            ui.showErrorMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main program.
     */
    public String run(String input) {
        String output;
        try {
            Command c = Parser.parse(input);
            assert c != null : "Command shouldn't be null";
            output = c.execute(tasks, ui, storage);
            if (c.isExit()) {
                Platform.exit();
            }
        } catch (AlisaException e) {
            return e.getMessage();
        }

        return output;
    }


    /**
     * Welcomes user to the program.
     *
     * @return Welcome message to user.
     */
    public String welcome() {
        return ui.showWelcomeMessage();
    }
}







