package Buu;
import java.time.LocalDateTime;

/**
 * Represents a command to add a new event task in the GPT application.
 * This command parses the user input to extract the task description, start time, and end time,
 * and adds the task to the task list if the input is valid.
 */
public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;

    /**
     * Constructs an EventCommand by parsing the user input to extract the task description,
     * start time, and end time. If the input format is invalid, the description, start time,
     * and end time will be set to null.
     *
     * @param input The user input string containing the command to add an event task.
     */
    public EventCommand(String input) {
        // Precondition: Ensure input is not null and correctly formatted
        assert input != null : "Input should not be null";

        String[] parts = input.substring(5).trim().split("/from|/to");
        if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            this.description = null;
            this.from = null;
            this.to = null;
        } else {
            this.description = parts[0].trim();
            this.from = parts[1].trim();
            this.to = parts[2].trim();
        }
    }

    /**
     * Executes the command to add a new event task to the task list. If the command format
     * is invalid, an error message is displayed. Otherwise, the task is added, saved, and a
     * confirmation message is shown.
     *
     * @param taskList The list of tasks to which the new task will be added.
     * @param ui       The user interface that displays messages to the user.
     * @param storage  The storage handler that manages task persistence.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Preconditions: Ensure taskList, ui, and storage are not null
        assert taskList != null : "TaskList should not be null";
        assert ui != null : "UI should not be null";
        assert storage != null : "Storage should not be null";

        if (description == null || from == null || to == null) {
            ui.showError(
                    "Invalid command format for event.\nUsage: event [task description] /from [start date/time] "
                            + "/to [end date/time]\n"
                            + "Example: event project meeting /from 2/12/2019 1400 /to 2/12/2019 1600"
            );
            return;
        }

        LocalDateTime fromDateTime = Parser.parseDateTime(from);
        LocalDateTime toDateTime = Parser.parseDateTime(to);
        if (fromDateTime != null && toDateTime != null) {
            Task newTask = new Event(description, fromDateTime, toDateTime);
            taskList.addTask(newTask);
            storage.saveTasks(taskList.getTasks());
            ui.showTaskAdded(newTask, taskList.getTasks().size());
        } else {
            ui.showError("The task was not added because of an invalid date or time.");
        }
    }
}
