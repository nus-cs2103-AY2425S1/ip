package Buu;
import java.time.LocalDateTime;

/**
 * Represents a command to add a new deadline task in the GPT application.
 * This command parses the user input to extract the task description and deadline,
 * and adds the task to the task list if the input is valid.
 */
public class DeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Constructs a DeadlineCommand by parsing the user input to extract the task description
     * and deadline. If the input format is invalid, the description and deadline will be set to null.
     *
     * @param input The user input string containing the command to add a deadline task.
     */
    public DeadlineCommand(String input) {
        // Precondition: input must not be null
        assert input != null : "Input should not be null";

        String[] parts = input.substring(8).trim().split("/by");
        if (parts.length < 2 || parts[0].trim().isEmpty()) {
            this.description = null;
            this.by = null;
        } else {
            this.description = parts[0].trim();
            this.by = parts[1].trim();
        }
    }

    /**
     * Executes the command to add a new deadline task to the task list. If the command format
     * is invalid, an error message is displayed. Otherwise, the task is added, saved, and a
     * confirmation message is shown.
     *
     * @param taskList The list of tasks to which the new task will be added.
     * @param ui       The user interface that displays messages to the user.
     * @param storage  The storage handler that manages task persistence.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Precondition: taskList, ui, and storage should not be null
        assert taskList != null : "TaskList should not be null";
        assert ui != null : "UI should not be null";
        assert storage != null : "Storage should not be null";

        if (description == null || by == null) {
            ui.showError(
                    "Invalid command format for deadline.\nUsage: deadline [task description] /by [date/time]\n"
                            + "Example: deadline return book /by 2/12/2019 1800"
            );
            return;
        }

        LocalDateTime byDateTime = Parser.parseDateTime(by);
        if (byDateTime != null) {
            Task newTask = new Deadline(description, byDateTime);
            taskList.addTask(newTask);
            storage.saveTasks(taskList.getTasks());
            ui.showTaskAdded(newTask, taskList.getTasks().size());
        } else {
            ui.showError("The task was not added because of an invalid date.");
        }
    }
}
