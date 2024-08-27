package delta;

import delta.util.Storage;
import delta.util.TaskList;
import delta.util.Ui;
import delta.util.Parser;

import delta.command.Command;

import delta.exception.DeltaException;

/**
 * Delta is a chatbot to assist in task management.
 * The permitted commands are:
 *      * todo [description]
 *      * deadline [description] /by [date/time]
 *      * event [description] /from [start] /to [end]
 *      * mark [index of task]
 *      * unmark [index of task]
 *      * delete [index of task]
 */
public class Delta {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Delta instance.
     *
     * @param filePath Relative file path of save file for Delta ChatBot.
     */
    public Delta(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        }
        catch (DeltaException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs main logic of Delta ChatBot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks,ui, storage);
                isExit = c.isExit();
            }
            catch (DeltaException e) {
                ui.showError(e.getMessage());
            }
            finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Delta("data/tasks.txt").run();
    }
}
