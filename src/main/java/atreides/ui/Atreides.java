package atreides.ui;

import java.util.ArrayList;

import atreides.command.Command;
import atreides.task.TaskList;

/**
 * Represents the logic for the entire chatbot
 */
public class Atreides {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isFileFound;


    /**
     * reads the lists of tasks in the filePath
     * @param filePath
     */
    public Atreides(String filePath) {
        this.ui = new Ui();
        storage = new Storage(filePath);
        try {
            ArrayList<String[]> list = storage.load();
            tasks = new TaskList(list);
            isFileFound = true;
        } catch (AtreidesException e) {
            isFileFound = false;
            tasks = new TaskList();
        }
    }

    /**
     * Accepts user commands and modifies the list of tasks accordingly
     */
    public void run() {
        ui.showWelcome();
        if (!isFileFound) {
            ui.showLoadingError();
        }
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                if (isExit) {
                    storage.writeTasks(tasks);
                    ui.showExit();
                }
            } catch (AtreidesException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Processes the user input to generate appropriate responses based on the parsed command.
     *
     * @param input the user input string to be parsed and executed
     * @return the response generated by executing the parsed command
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            Boolean isExit = c.isExit();
            if (isExit) {
                storage.writeTasks(tasks);
                return ui.showStringExit();
            }
            return c.executeString(tasks, ui, storage);
        } catch (AtreidesException e) {
            return e.getMessage();
        }
    }

    /**
     * Generates a welcome message for the user. If the file is not found, it will also show a loading error message.
     *
     * @return The welcome message, followed by a loading error message if the file is not found.
     */
    public String showWelcome() {
        if (!isFileFound) {
            return ui.showStringWelcome() + '\n' + ui.showStringLoadingError();
        }
        return ui.showStringWelcome();
    }

    public static void main(String[] args) {
        new Atreides("Atreides.txt").run();
    }
}
