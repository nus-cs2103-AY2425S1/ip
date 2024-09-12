package alisa;

import alisa.command.Command;
import alisa.exception.AlisaException;
import alisa.task.TaskList;

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
     *
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showDivider();
                Command c = Parser.parse(fullCommand);
                assert c != null : "Command shouldn't be null";
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (AlisaException e) {
                ui.showErrorMessage(e.getMessage());
            } finally {
                ui.showDivider();
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Alisa heard: " + input;
    }

    public static void main(String[] args) {
        new Alisa("data/tasks.txt").run();
    }
}







