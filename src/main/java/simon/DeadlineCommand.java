package simon;

import java.time.LocalDateTime;
/**
 * Represents a command to add a Deadline task to the task list.
 * Implements the Command interface to define the execution behavior for adding Deadline tasks.
 */
public class DeadlineCommand implements Command {
    String name;
    String deadLineString;
    LocalDateTime deadline;
    /**
     * Constructs a DeadlineCommand with the specified task name and deadline.
     *
     * @param name the name of the task
     * @param deadline the deadline of the task as a LocalDateTime object
     */
    public DeadlineCommand(String name, LocalDateTime deadline) {
        this.name = name;
        this.deadline = deadline;
    }
    /**
     * Executes the command to add a Deadline task to the task list.
     * The task is added to the task list, displayed to the user, and saved to the storage.
     *
     * @param taskList the list of tasks to which the new task will be added
     * @param ui the user interface used to show feedback to the user
     * @param storage the storage used to save the updated task list
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Deadline task = new Deadline(name, taskList.size(), deadline);
        taskList.add(task);
        ui.showTaskAdded(task, taskList.size());
        storage.saveToFile(taskList.toArr());

    }
}
