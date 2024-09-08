package chacha.command;

import chacha.ChaCha;
import chacha.ChaChaException;
import chacha.Storage;
import chacha.Ui;
import chacha.task.Task;
import chacha.task.TaskList;

/**
 * Represents the command to add a ToDo Task.
 */
public class ToDoCommand extends Command {

    public ToDoCommand(ChaCha chacha) {
        super(chacha);
    }

    /**
     * Returns the string representation of response to adding To Do task.
     *
     * @param userInput
     * @param storage
     * @param ui
     * @param tasks
     * @return String representation.
     */
    @Override
    public String execute(String userInput, Storage storage, Ui ui, TaskList tasks) {

        try {
            Task taskAdded = tasks.addToDo(userInput, ui, storage);

            return ui.printAdd(taskAdded, tasks);

        } catch (ChaChaException e) {
            return e.toString();
        }

    }
}
