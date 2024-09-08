package toothless.GUI;

import toothless.command.Command;
import toothless.command.Parser;
import toothless.exceptions.ToothlessExceptions;
import toothless.storage.Storage;
import toothless.task.TaskList;
import toothless.ui.Ui;

/**
 * Toothless represents a simple chat application.
 */
public class Toothless {

    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Constructor for Toothless.
     * Initializes the task list.
     */
    public Toothless() {
        this.storage = new Storage();
        this.taskList = new TaskList(storage.loadTasks());
        this.ui = new Ui();
    }

    public String getResponse(String userInput) {
        Command command = Parser.getCommandType(userInput);

        if (userInput.equals("bye")) {
            return ui.bye();
        }

        if (userInput.equals("hi")) {
            return ui.greeting();
        }

        try {
            return command.executeCommand(taskList, ui, storage);
        } catch (ToothlessExceptions e) {
            return e.getMessage();
        }
    }
}
