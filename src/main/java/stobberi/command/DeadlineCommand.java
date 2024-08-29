package stobberi.command;

import stobberi.task.Deadline;
import stobberi.stobberiException.EmptyStobberiException;
import stobberi.stobberiException.StobberiException;
import stobberi.stobberiException.WrongDateTimeStobberiException;
import stobberi.components.TaskList;

import java.time.format.DateTimeParseException;

/**
 * Represents a command to add a new deadline task to a {@link TaskList}.
 */
public class DeadlineCommand extends Command {
    /**
     * The list of tasks to which the deadline task will be added.
     */
    private TaskList taskList;

    /**
     * The description of the deadline task, including the task description and the deadline date/time.
     */
    private String descriptions;

    /**
     * Constructs a new {@code DeadlineCommand} with the specified {@link TaskList} and task description.
     *
     * @param taskList    The list of tasks to which the new deadline task will be added.
     * @param descriptions The description of the deadline task, including the task description and deadline date/time.
     */
    public DeadlineCommand(TaskList taskList, String descriptions) {
        this.taskList = taskList;
        this.descriptions = descriptions;
    }

    /**
     * Executes the command by adding a new deadline task to the {@link TaskList}.
     * The description must be in the format "TASK_DESCRIPTION /by DATE_AND_TIME".
     * If the description is empty or the date/time format is incorrect, appropriate exceptions are thrown.
     *
     * @throws StobberiException if an error occurs during command execution, if the task description is empty,
     *                            or if the date/time format is incorrect.
     */
    @Override
    public void execute() throws StobberiException {
        if (descriptions.isEmpty()) {
            throw new EmptyStobberiException("Where is the task?");
        }
        String[] parts = descriptions.split(" /by ");
        try {
            taskList.addTask(new Deadline(parts[0], parts[1]));
        } catch (DateTimeParseException e) {
            throw new WrongDateTimeStobberiException("Date and Time needs to be in the format dd-MM-yyyy HHmm'hrs'\n Example: 27-12-2004 1700hrs\n");
        }
    }
}