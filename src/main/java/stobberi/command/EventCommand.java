package stobberi.command;

import java.time.format.DateTimeParseException;

import stobberi.components.TaskList;
import stobberi.stobberiexception.EmptyStobberiException;
import stobberi.stobberiexception.SameTaskStobberiException;
import stobberi.stobberiexception.StobberiException;
import stobberi.stobberiexception.WrongDateTimeStobberiException;
import stobberi.task.Event;

/**
 * Represents a command to add a new event task to a {@link TaskList}.
 */
public class EventCommand extends Command {

    /**
     * Constructs a new {@code EventCommand} with the specified {@link TaskList} and task description.
     *
     * @param taskList      The list of tasks to which the new event task will be added.
     * @param restOfCommands The description of the event task, including the task description,
     *                       start date/time, and end date/time.
     */
    public EventCommand(TaskList taskList, String restOfCommands) {
        super(taskList, restOfCommands);
    }

    /**
     * Executes the command by adding a new event task to the {@link TaskList}.
     * The description must be in the format: "TASK_DESCRIPTION /from START_DATE_AND_TIME /to END_DATE_AND_TIME".
     * If the description is empty or the date/time format is incorrect, appropriate exceptions are thrown.
     *
     * @return A string indicating the successful addition of the event task.
     * @throws StobberiException If an error occurs during command execution, if the task description is empty,
     *                           or if the date/time format is incorrect.
     */
    @Override
    public String execute() throws StobberiException {
        String descriptions = getRestOfCommand();

        if (descriptions.isEmpty()) {
            throw new EmptyStobberiException("Where is the task?");
        }
        if (getTaskList().hasTask(descriptions)) {
            throw new SameTaskStobberiException("This task has already been added!\n"
                    + "Pwease change your description!!");
        }

        String[] eventParts = descriptions.split(" /from ");
        String[] secondParts = eventParts[1].split(" /to ");

        String output;
        try {
            output = getTaskList().addTask(new Event(eventParts[0], secondParts[0], secondParts[1]));
        } catch (DateTimeParseException e) {
            throw new WrongDateTimeStobberiException(
                    "Date and Time needs to be in the format dd-MM-yyyy HHmm'hrs'\n Example: 27-12-2004 1700hrs\n");
        }
        return output;
    }
}
