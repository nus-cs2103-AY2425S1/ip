package delta;

import delta.command.Command;
import delta.exception.DeltaException;
import delta.gui.Main;
import delta.util.Parser;
import delta.util.Storage;
import delta.util.TaskList;
import delta.util.Ui;
import javafx.application.Application;

/**
 * Delta is a chatbot to assist in task management.
 * The permitted commands are:
 *      * todo [description]
 *      * deadline [description] /by [date/time]
 *      * event [description] /from [start] /to [end]
 *      * find [description]
 *      * mark [index of task]
 *      * unmark [index of task]
 *      * delete [index of task]
 *      * edit [index of task] [task attribute] [new value]
 *      * bye
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
        } catch (DeltaException e) {
            ui.showLine();
            ui.showError(e.getMessage());
            ui.showLine();
            tasks = new TaskList();
        }
    }

    /**
     * Runs main logic of Delta ChatBot for CLI.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DeltaException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Serves as main entry point of Delta ChatBot for CLI.
     */
    public static void main(String[] args) {
        // Command Line Interface
        // new Delta("data/tasks.txt").run();

        // JavaFX Interface
        Application.launch(Main.class, args);
    }

    /**
     * Returns Delta ChatBot response for a user input given by JavaFX UI.
     *
     * @param input User input string.
     * @return Delta ChatBot response.
     */
    public String getResponse(String input) {
        String response;
        try {
            Command c = Parser.parse(input);
            response = c.execute(tasks, ui, storage);
        } catch (DeltaException e) {
            response = e.getMessage();
        }
        return response;
    }
}
