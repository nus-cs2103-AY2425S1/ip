package atreides.ui;

import atreides.command.Command;
import atreides.task.TaskList;

import java.util.ArrayList;

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

    public String showWelcome() {
        if (!isFileFound) {
            return ui.showStringWelcome() + '\n' + ui.showStringLoadingError();
        }
        return ui.showStringWelcome();
    }

    public static void main(String[] args) {
        new Atreides("src/main/atreides.ui.Atreides.txt").run();
    }
}
