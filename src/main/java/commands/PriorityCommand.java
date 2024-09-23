package commands;

import main.TaskList;
import storage.Storage;
import ui.Ui;
import tasks.Task;

/**
 * Represents a command that finds tasks of a certain priority.
 * This command searches through a task list and returns tasks that match the priority.
 */
public class PriorityCommand extends Command {
    private final String priority;
    private final TaskList results;

    /**
     * Constructs a {@code PriorityCommand} with the specified priority.
     *
     * @param priority The priority of the task to search for (e.g., high, medium, low).
     */
    public PriorityCommand(String priority) {
        this.priority = priority.toLowerCase();  // Store priority in lowercase
        this.results = new TaskList();
    }

    /**
     * Executes the priority command by searching through the task list for tasks that match the priority.
     * The found tasks are then displayed to the user through the UI.
     *
     * @param taskList The list of tasks to search through.
     * @param ui       The UI to interact with the user.
     * @param storage  The storage object (not used in this command but required by the method signature).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        for (Task t : taskList.getTasks()) {
            if (t.getPriority().equalsIgnoreCase(priority)) {
                results.addTask(t);
            }
        }
        if (results.size() > 0) {
            ui.showFound(this.results);
        } else {
            ui.showMessage("No tasks found with priority: " + priority);
        }
    }
}
