package bestie.command;

import bestie.Storage;
import bestie.TaskList;
import bestie.Ui;
import bestie.task.Priority;

/**
 * Creates a command that will display all tasks of a particular priority to the user.
 */
public class PriorityCommand extends Command {

    private Priority priority;

    /**
     * Creates
     * @param priority
     */
    public PriorityCommand(Priority priority) {
        this.priority = priority;
    }

    /**
     * Executes the priority command by calling the method from the UI class to display list of tasks.
     *
     * @param tasks User's list of tasks.
     * @param ui User Interface object that executes printing of message to console.
     * @param storage Loads list of tasks from file and writes tasks to the bestie.txt file.
     * @return String displaying list of tasks with the particular priority.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTasksOfPriority(tasks.getTasks(), priority);
    }
}
