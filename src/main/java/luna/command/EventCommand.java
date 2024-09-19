package luna.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import luna.LunaException;
import luna.Storage;
import luna.TaskList;
import luna.task.Event;
import luna.task.Task;

/**
 * Represents a command to add event to list of tasks.
 */
public class EventCommand implements Command {
    private final Event event;
    private final Command previousCommand;

    /**
     * Creates a command to add event to list.
     *
     * @param inputs Inputs from user to create event task.
     */
    public EventCommand(String[] inputs, Command previousCommand) throws LunaException {
        if (inputs.length == 1 || inputs[1].trim().isEmpty() || inputs[1].trim().indexOf("/") == 0) {
            throw new LunaException("Enter description for event\n"
                    + "e.g. event project meeting /from 12/12/2024 12:00 /to 12/12/2024 16:00");
        }

        if (!inputs[1].contains("/from ") || !inputs[1].contains("/to ")) {
            throw new LunaException("Enter start and end time for event\n"
                    + "e.g. event [task] /from [startTime] /to [endTime]");
        }

        String[] event = inputs[1].split(" /");

        if (!(event[1].contains("from ") && event[1].trim().length() > 5)
                || !(event[2].contains("to ") && event[2].trim().length() > 3)) {
            throw new LunaException("Enter start and end time for event using the format:\n"
                    + "event [task] /from [dd/MM/yyyy HH:mm] /to [dd/MM/yyyy HH:mm]");
        }

        LocalDateTime startTime;
        LocalDateTime endTime;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            startTime = LocalDateTime.parse(event[1].substring(5), formatter);
            endTime = LocalDateTime.parse(event[2].substring(3), formatter);

            if (startTime.isAfter(endTime)) {
                throw new LunaException("Invalid Event: Start is after End");
            }

            if (startTime.isBefore(LocalDateTime.now())) {
                throw new LunaException("Invalid Event: Start is before current time");
            }
        } catch (DateTimeParseException e) {
            throw new LunaException("Enter start and end time using format: [dd/MM/yyyy HH:mm]\n"
                    + "eg. 14/02/2024 14:30");
        }

        this.event = new Event(event[0].trim(), startTime, endTime);
        this.previousCommand = previousCommand;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.addTask(event, storage);
    }

    @Override
    public String undo(TaskList tasks, Storage storage) throws LunaException {
        int taskNumber = tasks.getTasks().indexOf(event);
        Task deleted = tasks.deleteTask(taskNumber, storage);
        return ">>> undo 'event' command\n"
                + "I've removed this task:\n"
                + "  " + deleted + "\n"
                + "Now you have " + tasks.getTasks().size() + " tasks in the list.";
    }

    @Override
    public Command getPreviousCommand() {
        return previousCommand;
    }
}
