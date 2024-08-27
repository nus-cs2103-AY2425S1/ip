package GPT;
/**
 * Represents a command to add a new ToDo task in the GPT application.
 * This command parses the user input to extract the task description and adds the task to the task list if the input is valid.
 */
public class TodoCommand extends Command {
    private String description;

    /**
     * Constructs a TodoCommand by parsing the user input to extract the task description.
     *
     * @param input The user input string containing the command to add a ToDo task.
     */
    public TodoCommand(String input) {
        this.description = input.substring(4).trim();
    }

    /**
     * Executes the command to add a new ToDo task to the task list.
     * If the command format is invalid (i.e., the description is empty), an error message is displayed.
     * Otherwise, the task is added, saved, and a confirmation message is shown.
     *
     * @param taskList The list of tasks to which the new task will be added.
     * @param ui       The user interface that displays messages to the user.
     * @param storage  The storage handler that manages task persistence.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (description.isEmpty()) {
            ui.showError("The description of a todo cannot be empty.\nUsage: todo [task description]\nExample: todo read a book");
            return;
        }
        Task newTask = new ToDo(description);
        taskList.addTask(newTask);
        storage.saveTasks(taskList.getTasks());
        ui.showTaskAdded(newTask, taskList.getTasks().size());
    }
}
