package Buu;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to add an Event task in the GPT application.
 * This command parses the description, start date, and end date strings from the user input
 * and converts the date strings into LocalDateTime objects.
 */
public class EventCommand extends Command {

    private String description;
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an EventCommand by parsing the user input to extract
     * the task description, start date, and end date.
     *
     * @param input The user input string containing the event command.
     */
    public EventCommand(String input) {
        // Parse the input
        String[] parts = input.split(" /from | /to ");

        // Ensure the correct number of parts
        if (parts.length != 3) {
            this.description = ""; // Set to empty to handle later
            return; // No further processing if the format is invalid
        }

        // Extract the description, start date, and end date
        this.description = parts[0].substring("event ".length()).trim();

        try {
            // Use the parseDateTime method from the Parser class
            this.from = Parser.parseDateTime(parts[1].trim());
            this.to = Parser.parseDateTime(parts[2].trim());
        } catch (DateTimeParseException e) {
            this.from = null; // Set to null to handle later
            this.to = null;
        }
    }

    /**
     * Executes the command to add the Event task to the task list.
     *
     * @param taskList The task list where the new task will be added.
     * @param ui       The user interface to show the task addition.
     * @param storage  The storage handler to save the task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Check for empty description
        if (description.isEmpty()) {
            ui.showError("The description of an event cannot be empty.\n"
                    + "Usage: event DESCRIPTION /from <yyyy-MM-dd HH:mm> /to <yyyy-MM-dd HH:mm>\n"
                    + "Example: event Celebration /from 2024-09-20 20:00 /to 2024-09-20 23:00\n");
            return;
        }

        // Check for parsing errors
        if (from == null || to == null) {
            ui.showError("Invalid date format. Please use yyyy-MM-dd HH:mm.");
            return;
        }

        // Create Event task with LocalDateTime
        Task task = new Event(description, from, to);
        taskList.addTask(task);
        storage.saveTasks(taskList.getTasks());
        ui.showTaskAdded(task, taskList.getTasks().size());
    }
}
