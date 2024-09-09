package gravitas.command;

import gravitas.exception.DukeException;
import gravitas.storage.Storage;
import gravitas.task.Task;
import gravitas.tasklist.TaskList;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {

    private static final String OUT_OF_BOUND = "The task that you wish to mark is invalid! please try again!";
    private static final String EMPTY_DELETE = "OOPS!!! The description of a delete cannot be empty.";
    private static final String DELETE_MESSAGE = "Noted. I've removed this task:";
    private String userInput;

    /**
     * Constructor for DeleteCommand class.
     */
    public DeleteCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Parses the user input and delete the respective task.
     *
     * @throws DukeException If the user input is invalid.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        String[] msgFrag = userInput.split(" ", 2);
        if (msgFrag.length <= 1) {
            throw new DukeException(EMPTY_DELETE);
        }
        int index = Integer.parseInt((msgFrag[1])) - 1;
        if (index >= taskList.size() || index < 0) {
            throw new DukeException(OUT_OF_BOUND);
        }

        Task task = taskList.getTask(index);
        assert task != null : "Task should not be null";
        taskList.removeTask(index);
        return (DELETE_MESSAGE + "\n" + taskList.printTask(task));
    }
}
