package stobberi.command;

import java.time.format.DateTimeParseException;

import stobberi.components.TaskList;
import stobberi.stobberiexception.EmptyStobberiException;
import stobberi.stobberiexception.SameTaskStobberiException;
import stobberi.stobberiexception.StobberiException;
import stobberi.stobberiexception.WrongDateTimeStobberiException;
import stobberi.task.Deadline;

/**
 * Represents a command to add a new deadline task to a {@link TaskList}.
 * The command processes a task description and its associated deadline.
 */
public class DeadlineCommand extends Command {

    /**
     * Constructs a new {@code DeadlineCommand} with the specified {@link TaskList} and task description.
     *
     * @param taskList     The list of tasks to which the new deadline task will be added.
     * @param restOfCommand The string containing the task description and deadline date/time.
     */
    public DeadlineCommand(TaskList taskList, String restOfCommand) {
        super(taskList, restOfCommand);
    }

    /**
     * Executes the command by adding a new deadline task to the {@link TaskList}.
     * The task description must be in the format: "TASK_DESCRIPTION /by DATE_AND_TIME".
     * The date and time must follow the format "dd-MM-yyyy HHmm'hrs'".
     * If the task description is empty or the date/time format is incorrect, appropriate exceptions are thrown.
     *
     * @return A string indicating the successful addition of the deadline task.
     * @throws StobberiException If an error occurs during command execution, if the task description is empty,
     *                           if the task already exists in the list, or if the date/time format is invalid.
     */
    @Override
    public String execute() throws StobberiException {
        String output;
        String descriptions = getRestOfCommand();

        if (descriptions.isEmpty()) {
            throw new EmptyStobberiException("Where is the task???");
        }
        if (getTaskList().hasTask(descriptions)) {
            throw new SameTaskStobberiException("This task has already been added!\n"
                    + "Pwease change your description!!");
        }

        String[] parts = descriptions.split(" /by ");
        try {
            output = getTaskList().addTask(new Deadline(parts[0], parts[1]));
        } catch (DateTimeParseException e) {
            throw new WrongDateTimeStobberiException(
                    "Date and Time needs to be in the format dd-MM-yyyy HHmm'hrs'\n Example: 27-12-2004 1700hrs\n");
        }
        return output;
    }
}
