package chacha.command;

import chacha.ChaCha;
import chacha.Storage;
import chacha.Ui;
import chacha.exception.ChaChaException;
import chacha.task.Task;
import chacha.task.TaskList;

/**
 * Represents the command to add a ToDo Task.
 */
public class ToDoCommand extends Command {
    private static final String MISSING_FIELD = "What task are you intending to add as a \'todo\'?\n"
            + "Please type again!";

    public ToDoCommand(ChaCha chacha) {
        super(chacha);
    }

    /**
     * Returns the string representation of response to adding To Do task.
     *
     * @param userInput User input
     * @param storage Storage of ChaCha
     * @param ui UI of ChaCha
     * @param tasks List of tasks
     * @return String representation.
     */
    @Override
    public String execute(String userInput, Storage storage, Ui ui, TaskList tasks) {
        try {
            Task taskAdded = tasks.addToDo(userInput, ui, storage);
            return ui.printAdd(taskAdded, tasks);
        } catch (ChaChaException e) {
            return MISSING_FIELD;
        }
    }
}
