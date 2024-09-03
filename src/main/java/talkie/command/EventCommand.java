package talkie.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import talkie.components.Storage;
import talkie.components.Ui;
import talkie.exception.TalkieMissingArgumentException;
import talkie.task.Event;
import talkie.task.Task;
import talkie.task.TaskList;

/**
 * Represents a command to add a new event task to the task list in the Talkie application.
 * The command processes user input to create an event with a description, start time, and end time.
 */
public class EventCommand extends Command {

    private String fullCommand;

    /**
     * Constructs a new {@code EventCommand} with the full user input.
     *
     * @param fullCommand The full user input containing the command type and the details of the event.
     */
    public EventCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the {@code EventCommand} by parsing the input to add a new event task to the task list.
     * <p>
     * The event task includes a description, start time, and end time. The input is expected to follow the format:
     * <code>event description /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm</code>. If the input is missing any required
     * components or if the date/time format is incorrect, appropriate exceptions are thrown.
     * </p>
     *
     * @param tasks   The {@code TaskList} containing all current tasks.
     * @param ui      The {@code Ui} component used to display messages to the user.
     * @param storage The {@code Storage} component used to save task data.
     * @return A string containing a confirmation message about the added event or an error message if the input
     *         is invalid.
     * @throws TalkieMissingArgumentException If the command is missing the required description, start time,
     *         or end time.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TalkieMissingArgumentException {
        String[] parts = fullCommand.split(" ", 2); // Split into command type and the rest of the input

        try {
            if (parts.length == 2) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                String details = parts[1]; // Rest of the input (e.g., description, from, to details)
                String[] eventParts = details.split("/from | /to ");

                if (eventParts.length < 3) {
                    throw new TalkieMissingArgumentException(parts[0],
                            "The 'description', 'from', and 'to' of event cannot be empty.");
                }

                String description = eventParts[0].trim();
                String from = eventParts[1].trim();
                String to = eventParts[2].trim();

                LocalDateTime startTime = LocalDateTime.parse(from, formatter);
                LocalDateTime endTime = LocalDateTime.parse(to, formatter);

                if (startTime.isAfter(endTime)) {
                    return "The end time must be after the start time!";
                }

                Task newEvent = new Event(description, startTime, endTime);
                tasks.addTask(newEvent);
                return ui.addMessage(newEvent, tasks.size());
            } else {
                throw new TalkieMissingArgumentException(parts[0],
                        "The 'description', 'from', and 'to' of event cannot be empty.");
            }
        } catch (DateTimeParseException e) {
            return ui.wrongDateTimeFormatMessage();
        }
    }

    /**
     * Indicates that this command does not terminate the application.
     *
     * @return {@code false}, as this command does not end the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
