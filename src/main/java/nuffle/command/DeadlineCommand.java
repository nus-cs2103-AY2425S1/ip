package nuffle.command;

import nuffle.exception.NuffleException;
import nuffle.storage.Storage;
import nuffle.task.Deadline;
import nuffle.task.Task;
import nuffle.task.TaskList;
import nuffle.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static nuffle.parser.Parser.isValidateDateTimeFormat;

public class DeadlineCommand extends Command {
    private final String userInput;

    public DeadlineCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws NuffleException {
        assert userInput != null && !userInput.trim().isEmpty() : "Command should not be null or empty.";
        if (!userInput.contains("/by")) {
            throw NuffleException.checkDeadlineFormat();
        }
        String[] desc = userInput.substring(8).split(" /by ");
        assert desc.length > 0 : "description array should have at least one element.";
        if (userInput.substring(8).trim().isEmpty()) {
            throw NuffleException.noDesc("deadline");
        }
        if (desc.length < 2 || desc[1].trim().isEmpty()) {
            throw NuffleException.checkDeadlineParams();
        }
        assert !desc[1].equals("") : "DateTime strings for event should not be null";
        // check that the date input is of the correct format (yyyy-mm-dd hhmm)
        if (!isValidateDateTimeFormat(desc[1])) {
            throw NuffleException.checkDateTimeFormat();
        }
        String dateTime = desc[1].trim();
        // Parse the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime by = LocalDateTime.parse(dateTime, formatter);
        Task task = new Deadline(desc[0].trim(), by);
        tasks.addTask(task);
        return ui.addTaskMessage(task, tasks.getSize());
    }

    public boolean isTerminateProgram() {
        return false;
    }
}
