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
     * @throws IllegalArgumentException If the input format is invalid.
     */
    public DeadlineCommand(String input) throws IllegalArgumentException {
        // Parse the input
        String[] parts = input.split(" /by ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid deadline format\n."
                    + "Use: deadline <description> /by <yyyy-MM-dd HH:mm>");
        }

        // Extract the description and due date
        this.description = parts[0].substring("deadline ".length()).trim();

        try {
            // Use the parseDateTime method from the Parser class
            this.by = Parser.parseDateTime(parts[1].trim());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd HH:mm.");
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
        Task task = new Deadline(description, by); // Create Deadline task with LocalDateTime
        taskList.addTask(task);
        storage.saveTasks(taskList.getTasks());
        ui.showTaskAdded(task, taskList.getTasks().size());
    }
}
