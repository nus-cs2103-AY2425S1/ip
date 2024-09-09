package gravitas.command;

import gravitas.exception.DukeException;
import gravitas.storage.Storage;
import gravitas.task.Task;
import gravitas.tasklist.TaskList;

/**
 * Command to update a task.
 */
public class UpdateTaskCommand extends Command {

    private static final String OUT_OF_BOUND = "The task that you wish to mark is invalid! please try again!";
    private static final String MARK = "Nice! I've marked this task as done:";
    private static final String UNMARK = "OK, I've marked this task as not done yet:";
    private String userInput;

    /**
     * Constructor for DeleteCommand class.
     */
    public UpdateTaskCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Returns a message and mark/unmark the respective task.
     *
     * @return String message to be printed.
     * @throws DukeException If the user input is invalid.
     */
    @Override
    public String executeCommand(TaskList taskList, Storage storage) throws DukeException {
        String[] msgFrag = userInput.split(" ", 2);
        int index = Integer.parseInt((msgFrag[1])) - 1;

        if (index >= taskList.size() || index < 0) {
            throw new DukeException(OUT_OF_BOUND);
        }
        Task task = taskList.getTask(index);

        if (msgFrag[0].equals("mark")) {
            task.markTask();
            return (MARK + "\n" + taskList.printTask(task));
        } else {
            task.unMarkTask();
            System.out.println(UNMARK);
            return (UNMARK + "\n" + taskList.printTask(task));
        }
    }
}
