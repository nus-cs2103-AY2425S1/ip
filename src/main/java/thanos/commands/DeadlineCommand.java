package thanos.commands;

import static thanos.utility.ResponseFormatter.generateTaskAddedResponse;

import java.time.LocalDateTime;

import thanos.exceptions.InvalidCommandException;
import thanos.tasks.Deadline;
import thanos.tasks.TaskList;
import thanos.utility.DateTimeUtility;

/**
 * Represents a command to add a deadline task to the {@code TaskList}.
 */
public class DeadlineCommand extends Command {
    /**
     * Constructs a {@code DeadlineCommand} with the given argument.
     *
     * @param argument the argument associated with this command. The argument should be a string in the format
     *                 'deadline [task] /by [due date]'.
     */
    public DeadlineCommand(String argument) {
        super(argument);
    }

    /**
     * Executes the command to add a deadline task to the {@code TaskList}.
     * <p>
     * This method parses the command argument to extract the task description and due date. It then creates a new
     * {@code Deadline} object with these details, adds it to the {@code TaskList}, and returns a formatted string
     * confirming the addition of the task. If the argument is incorrectly formatted or the date cannot be parsed,
     * an {@code InvalidCommandException} is thrown.
     * </p>
     *
     * @param taskList the list of tasks to which the new deadline task will be added.
     * @return a formatted string confirming the addition of the deadline task and displaying the updated task count.
     * @throws InvalidCommandException if the argument is not in the correct format or if the date cannot be parsed.
     */
    @Override
    public String execute(TaskList taskList) throws InvalidCommandException {
        String[] detailsArr = this.getArgument().split(" /by ");
        if (detailsArr.length != 2) {
            throw new InvalidCommandException(
                    "Invalid input format. Please use the correct format: 'deadline [task] /by [due date]'"
            );
        }

        String description = detailsArr[0];
        LocalDateTime date = DateTimeUtility.parse(detailsArr[1]);
        if (date == null) {
            return "";
        }
        Deadline deadline = new Deadline(description, date);
        taskList.add(deadline);
        return generateTaskAddedResponse(deadline, taskList.size());
    }
}
