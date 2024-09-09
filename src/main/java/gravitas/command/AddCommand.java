package gravitas.command;

import gravitas.exception.DukeException;
import gravitas.storage.Storage;
import gravitas.task.Deadline;
import gravitas.task.Event;
import gravitas.task.Task;
import gravitas.task.Todo;
import gravitas.tasklist.TaskList;

/**
 * Represents the command to add a task to the tasklist.
 */
public class AddCommand extends Command {

    private static final String EMPTY_TODO = "OOPS!!! The description of a todo cannot be empty.";
    private static final String EMPTY_EVENT = "OOPS!!! The description of a event cannot be empty "
            + "and must contain /from and /to.";
    private static final String EMPTY_DEADLINE = "OOPS!!! The description of a deadline "
            + "cannot be empty and must contain /by ";
    private static final String ADDED = "Got it. I've added this task:";
    private String userInput;

    /**
     * Constructor for AddCommand class.
     */
    public AddCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Parses the user input and save the user task into the tasklist.
     *
     * @throws DukeException If the user input is invalid.
     */
    @Override
    public String executeCommand(TaskList taskList, Storage storage) throws DukeException {
        String[] msgFrag = userInput.split(" ", 2);
        Task task;
        if (msgFrag[0].equals("deadline")) {
            if (msgFrag.length <= 1 || !userInput.contains("/by ")) {
                throw new DukeException(EMPTY_DEADLINE);
            }

            String[] deadline = userInput.split("/by ", 2);
            //description format: [deadline, description]
            String[] description = deadline[0].split(" ", 2);
            assert description.length == 2 : "Description should not be empty";
            task = new Deadline(description[1], deadline[1]);
        } else if (msgFrag[0].equals("event")) {
            if (msgFrag.length <= 1 || !userInput.contains("/from ") || !userInput.contains("/to ")) {
                throw new DukeException(EMPTY_EVENT);
            }

            String[] event = userInput.split("/from ", 2);
            String[] formattedDeadline = event[1].split("/to ", 2);
            task = new Event(event[0], formattedDeadline[0], formattedDeadline[1]);
        } else {
            if (msgFrag.length <= 1) {
                throw new DukeException(EMPTY_TODO);
            }

            task = new Todo(msgFrag[1]);
        }
        taskList.addTask(task);
        System.out.println(ADDED);
        return (ADDED + "\n" + taskList.printTask(task));
    }
}
