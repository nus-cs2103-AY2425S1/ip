package Buu;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to add a Deadline task in the GPT application.
 * This command parses the description and due date string from the user input
 * and converts the date string into a LocalDateTime object.
 */
public class DeadlineCommand extends Command {

    private String description;
    private LocalDateTime by;

    /**
     * Constructs a DeadlineCommand by parsing the user input to extract
     * the task description and due date.
     *
     * @param input The user input string containing the deadline command.
     */
    public DeadlineCommand(String input) {
        // Parse the input
        String[] parts = input.split(" /by ");

        // Ensure the correct number of parts
        if (parts.length != 2) {
            this.description = ""; // Set to empty to handle later
            return; // No further processing if the format is invalid
        }

        // Extract the description and due date
        this.description = parts[0].substring("deadline ".length()).trim();

        try {
            // Use the parseDateTime method from the Parser class
            this.by = Parser.parseDateTime(parts[1].trim());
        } catch (DateTimeParseException e) {
            this.by = null; // Set to null to handle later
        }
    }

    /**
     * Executes the command to add the Deadline task to the task list.
     *
     * @param taskList The task list where the new task will be added.
     * @param ui       The user interface to show the task addition.
     * @param storage  The storage handler to save the task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Check for empty description
        if (description.isEmpty()) {
            ui.showError("The description of a deadline cannot be empty.\n"
                    + "Usage: deadline DESCRIPTION /by <yyyy-MM-dd HH:mm>\n"
                    + "Example: deadline Homework /by 2024-09-18 23:59\n");
            return;
        }

        // Check for parsing errors
        if (by == null) {
            ui.showError("Invalid date format. Please use yyyy-MM-dd HH:mm.");
            return;
        }

        // Create Deadline task with LocalDateTime
        Task task = new Deadline(description, by);
        taskList.addTask(task);
        storage.saveTasks(taskList.getTasks());
        ui.showTaskAdded(task, taskList.getTasks().size());
    }
}
