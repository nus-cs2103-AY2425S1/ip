package atreides.ui;

import atreides.command.Command;
import atreides.task.TaskList;

/**
 * Represents the logic for the entire chatbot
 */
public class Atreides {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * reads the lists of tasks in the filePath
     * @param filePath
     */
    public Atreides(String filePath) {
        this.ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (AtreidesException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Accepts user commands and modifies the list of tasks accordingly
     */
    public void run() {
        ui.showWelcome();
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

    public static void main(String[] args) {
        new Atreides("src/main/atreides.ui.Atreides.txt").run();
    }
}