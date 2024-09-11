package command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import exception.ElliotException;
import task.EventTask;
import utility.CustomDateTimeFormatter;
import utility.Storage;
import utility.Strip;
import utility.TaskList;
import utility.Ui;

/**
 * {@link EventCommand} adds {@link EventTask} to the {@link TaskList}.
 */
public class EventCommand extends Command {
    private final String taskDescription;
    private final LocalDateTime fromDateTime;
    private final LocalDateTime toDateTime;

    /**
     * Creates a {@link EventCommand} object without any information on the details
     * of the {@link Task}.
     */
    public EventCommand() {
        super();
        this.taskDescription = "";
        this.fromDateTime = LocalDateTime.now();
        this.toDateTime = LocalDateTime.now();
    }

    private EventCommand(String taskDescription, LocalDateTime fromDateTime,
            LocalDateTime toDateTime) {
        this.taskDescription = taskDescription;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    /**
     * Parses the input string accordingly into each respective attributes.
     * Requires Date and Time in the correct form for both {@code from} and {@code to}.
     *
     * @param unparsedArguments complete string of unparsed argument.
     * @return a new {@link EventCommand} with the correctly parsed argument.
     * @throws ElliotException If command arguments are invalid or incomplete.
     */
    @Override
    public Command parseArguments(String unparsedArguments) throws ElliotException {
        String[] firstSplit = Strip
                .stripStringArray(unparsedArguments.strip().split("/from|/to", 2));
        if (firstSplit[0] == "" || firstSplit.length == 0) {
            throw new ElliotException("give event a description\n");
        }
        if (firstSplit.length < 2) {
            throw new ElliotException("from when to when is this event?\n");
        }
        String[] secondSplit;
        String fromDateTimeString;
        String toDateTimeString;
        if (firstSplit[1].contains("/to")) {
            secondSplit = Strip.stripStringArray(firstSplit[1].split("/to", 2));
            if (secondSplit.length < 2) {
                throw new ElliotException("invalid time\n");
            }
            fromDateTimeString = secondSplit[0];
            toDateTimeString = secondSplit[1];
        } else {
            secondSplit = Strip.stripStringArray(firstSplit[1].split("/from", 2));
            if (secondSplit.length < 2) {
                throw new ElliotException("invalid time\n");
            }
            fromDateTimeString = secondSplit[1];
            toDateTimeString = secondSplit[0];
        }
        try {
            LocalDateTime resolvedFromDateTime = LocalDateTime.parse(fromDateTimeString,
                    CustomDateTimeFormatter.DATE_TIME_FORMATTER);
            LocalDateTime resolvedToDateTime = LocalDateTime.parse(toDateTimeString,
                    CustomDateTimeFormatter.DATE_TIME_FORMATTER);
            return new EventCommand(firstSplit[0], resolvedFromDateTime, resolvedToDateTime);
        } catch (DateTimeParseException e) {
            throw new ElliotException("date format incorrect. try dd-MM-yyyy hhmm (24hr)\n", e);
        }
    }

    /**
     * Adds {@link EventTask} to the {@link TaskList}.
     *
     * @param taskList {@link TaskList} of which the {@link EventTask} will be added to.
     * @param storage not used in this command.
     * @return modified {@link TaskList} with the added {@link EventTask}.
     */
    @Override
    public TaskList runCommand(TaskList taskList, Storage storage) {
        TaskList newTaskList = taskList
            .addTask(new EventTask(taskDescription, fromDateTime, toDateTime));
        Ui.say("Got it. I've added this task:\n"
                + newTaskList.get(newTaskList.size() - 1) + "\n"
                + "Now you have " + newTaskList.size() + " tasks in the list.\n");
        return newTaskList;
    }
}
