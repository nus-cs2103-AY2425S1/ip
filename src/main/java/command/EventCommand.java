package command;

import exception.ElliotException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import task.EventTask;
import utility.Storage;
import utility.Strip;
import utility.TaskList;
import utility.Ui;
import utility.CustomDateTimeFormatter;

public class EventCommand extends Command {
    private final String taskDescription;
    private final LocalDateTime fromDateTime;
    private final LocalDateTime toDateTime;

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

    @Override
    public Command parseArguments(String unparsedArguments) throws ElliotException {
        String[] firstSplit = Strip
                .stripStringArray(unparsedArguments.strip().split("/from|/to", 2));
        if (firstSplit[0] == "" || firstSplit.length == 0) {
            Ui.say("give event a description\n");
            throw new ElliotException();
        }
        if (firstSplit.length < 2) {
            Ui.say("from when to when is this event?\n");
            throw new ElliotException();
        }
        String[] secondSplit;
        String fromDateTimeString;
        String toDateTimeString;
        if (firstSplit[1].contains("/to")) {
            secondSplit = Strip.stripStringArray(firstSplit[1].split("/to", 2));
            if (secondSplit.length < 2) {
                Ui.say("invalid time\n");
                throw new ElliotException();
            }
            fromDateTimeString = secondSplit[0];
            toDateTimeString = secondSplit[1];
        } else {
            secondSplit = Strip.stripStringArray(firstSplit[1].split("/from", 2));
            if (secondSplit.length < 2) {
                Ui.say("invalid time\n");
                throw new ElliotException();
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
            Ui.say("date format incorrect. try dd-MM-yyyy hhmm (24hr)\n");
            throw new ElliotException(e);
        }
    }

    @Override
    public TaskList runCommand(TaskList taskList, Storage storage) {
        taskList = taskList.addTask(new EventTask(taskDescription, fromDateTime, toDateTime));
        Ui.say("Got it. I've added this task:\n"
                + taskList.get(taskList.size() - 1) + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.\n");
        return taskList;
    }
}
