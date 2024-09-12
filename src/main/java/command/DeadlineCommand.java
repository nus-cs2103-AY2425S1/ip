package command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import exception.ElliotException;
import task.DeadlineTask;
import utility.CustomDateTimeFormatter;
import utility.Storage;
import utility.Strip;
import utility.TaskList;
import utility.Ui;

/**
 * {@link Command} to add {@link DeadlineTask} to the {@link TaskList}.
 */
public class DeadlineCommand extends Command {
    private final String taskDescription;
    private final LocalDateTime deadlineDateTime;

    /**
     * Creates a {@link DeadlineCommand} object without any information on
     * the details of the {@link Task}.
     */
    public DeadlineCommand() {
        super();
        this.taskDescription = "";
        this.deadlineDateTime = LocalDateTime.now();
    }

    private DeadlineCommand(String taskDescription, LocalDateTime deadlineDateTime) {
        this.taskDescription = taskDescription;
        this.deadlineDateTime = deadlineDateTime;
    }

    /**
     * Parses the input string accordingly into each respective attributes.
     * Requires Date and Time in the correct form for {@code by}.
     *
     * @param unparsedArguments complete string of unparsed argument.
     * @return a new {@link DeadlineCommand} with the correctly parsed argument.
     * @throws ElliotException If command arguments are invalid or incomplete.
     */
    @Override
    public Command parseArguments(String unparsedArguments) throws ElliotException {
        String[] splittedArguments = Strip
                .stripStringArray(unparsedArguments.strip().split("/by", 2));
        if (splittedArguments.length == 0 || splittedArguments[0] == "") {
            throw new ElliotException("give task a description\n");
        }
        if (splittedArguments.length < 2) {
            throw new ElliotException("when is this due by?\n");
        }
        assert(splittedArguments.length == 2);
        try {
            LocalDateTime resolvedDateTime = LocalDateTime.parse(splittedArguments[1],
                    CustomDateTimeFormatter.DATE_TIME_FORMATTER);
            return new DeadlineCommand(splittedArguments[0], resolvedDateTime);
        } catch (DateTimeParseException e) {
            throw new ElliotException("date format incorrect. try dd-MM-yyyy hhmm (24hr)\n", e);
        }
    }

    /**
     * Adds {@link DeadlineTask} to the {@link TaskList} and prints a success message.
     *
     * @param taskList the {@link TaskList} to which the {@link DeadlineTask} will be added to.
     * @param storage  not used in this command.
     * @return modified {@link TaskList} with the added {@link DeadlineTask}.
     */
    @Override
    public TaskList runCommand(TaskList taskList, Storage storage) {
        assert(taskDescription != "");
        TaskList newTaskList = taskList
            .addTask(new DeadlineTask(taskDescription, deadlineDateTime));
        Ui.say("Got it. I've added this task:\n"
                + newTaskList.get(newTaskList.size() - 1) + "\n"
                + "Now you have " + newTaskList.size() + " tasks in the list.\n");
        return newTaskList;
    }
}
