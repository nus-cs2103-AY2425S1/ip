package command;

import exception.ElliotException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import task.DeadlineTask;
import utility.Storage;
import utility.Strip;
import utility.TaskList;
import utility.Ui;
import utility.CustomDateTimeFormatter;

public class DeadlineCommand extends Command {
    private final String taskDescription;
    private final LocalDateTime deadlineDateTime;

    public DeadlineCommand() {
        super();
        this.taskDescription = "";
        this.deadlineDateTime = LocalDateTime.now();
    }

    private DeadlineCommand(String taskDescription, LocalDateTime deadlineDateTime) {
        this.taskDescription = taskDescription;
        this.deadlineDateTime = deadlineDateTime;
    } 

    @Override
    public Command parseArguments(String unparsedArguments) throws ElliotException {
        String[] splittedArguments = Strip
                .stripStringArray(unparsedArguments.strip().split("/by", 2));
        if (splittedArguments.length == 0 || splittedArguments[0] == "") {
            Ui.say("give task a description\n");
            throw new ElliotException();
        }
        if (splittedArguments.length < 2) {
            Ui.say("when is this due by?\n");
            throw new ElliotException();
        }
        try {
            LocalDateTime resolvedDateTime = LocalDateTime.parse(splittedArguments[1],
                    CustomDateTimeFormatter.DATE_TIME_FORMATTER);
            return new DeadlineCommand(splittedArguments[0], resolvedDateTime);
        } catch (DateTimeParseException e) {
            Ui.say("date format incorrect. try dd-MM-yyyy hhmm (24hr)\n");
            throw new ElliotException(e);
        }
    }

    @Override
    public TaskList runCommand(TaskList taskList, Storage storage) {
        taskList = taskList.addTask(new DeadlineTask(taskDescription, deadlineDateTime));
        Ui.say("Got it. I've added this task:\n"
                + taskList.get(taskList.size() - 1) + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.\n");
        return taskList;
    }
}
