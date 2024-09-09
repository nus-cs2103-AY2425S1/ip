package nuffle.command;

import nuffle.exception.NuffleException;
import nuffle.storage.Storage;
import nuffle.task.Event;
import nuffle.task.Task;
import nuffle.task.TaskList;
import nuffle.task.Todo;
import nuffle.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static nuffle.parser.Parser.isValidateDateTimeFormat;

public class EventCommand extends Command {
    private final String userInput;

    public EventCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws NuffleException {
        assert userInput != null && !userInput.trim().isEmpty() : "Command should not be null or empty";
        // Program will add an event task to the list

        if (!(userInput.contains("/from") || userInput.contains("/to"))) {
            throw NuffleException.checkEventFormat();
        }

        // Get the description of the event task first
        String[] desc = userInput.substring(5).split(" /from | /to ");
        if (userInput.substring(5).trim().isEmpty()) {
            throw NuffleException.noDesc("event");
        }
        // ensure that to / from has input
        if (desc.length < 3 || desc[1].trim().isEmpty() || desc[2].trim().isEmpty()) {
            throw NuffleException.checkEventParams();
        }
        assert !desc[1].equals("") && !desc[2].equals("") : "DateTime strings for event should not be null";
        // check that the date input is of the correct format (yyyy-mm-dd hhmm)
        if (!isValidateDateTimeFormat(desc[1]) || !isValidateDateTimeFormat(desc[2])) {
            throw NuffleException.checkDateTimeFormat();
        }
        // Parse the date and time
        String fromDateTime = desc[1].trim();
        String toDateTime = desc[2].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime from = LocalDateTime.parse(fromDateTime, formatter);
        LocalDateTime to = LocalDateTime.parse(toDateTime, formatter);
        Task task = new Event(desc[0].trim(), from, to);
        tasks.addTask(task);
        return ui.addTaskMessage(task, tasks.getSize());
    }

    public boolean isTerminateProgram() {
        return false;
    }
}
