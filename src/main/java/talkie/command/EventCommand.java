package talkie.command;

import talkie.components.Storage;
import talkie.components.Ui;
import talkie.exception.TalkieMissingArgumentException;
import talkie.task.Event;
import talkie.task.Task;
import talkie.task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
     * The event includes a description, start time, and end time.
     * If the input is invalid, such as missing or incorrectly formatted date/time, appropriate exceptions are thrown.
     *
     * @param tasks   The task list containing all current tasks.
     * @param ui      The UI component used to display messages to the user.
     * @param storage The storage component used to save task data.
     * @throws TalkieMissingArgumentException If the command is missing the required description, start time, or end time.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TalkieMissingArgumentException {
        String[] parts = fullCommand.split(" ", 2); // Split into command type and the rest of the input

        try {
            if (parts.length == 2) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                String details = parts[1]; // Rest of the input (e.g., description, from, to details)
                String[] eventParts = details.split("/from | /to ");

                String description = eventParts[0].trim();
                String from = eventParts[1].trim();
                String to = eventParts[2].trim();

                LocalDateTime startTime = LocalDateTime.parse(from, formatter);
                LocalDateTime endTime = LocalDateTime.parse(to, formatter);

                if (startTime.isAfter(endTime)) {
                    System.out.println("The end time must be after the start time!");
                    return;
                }

                Task newEvent = new Event(description, startTime, endTime);
                tasks.addTask(newEvent);
                ui.addMessage(newEvent, tasks.size());
            } else {
                throw new TalkieMissingArgumentException(parts[0],
                        "The 'description', 'from', and 'to' of event cannot be empty.");
            }
        } catch (DateTimeParseException e) {
            ui.wrongDateTimeFormatMessage();
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
