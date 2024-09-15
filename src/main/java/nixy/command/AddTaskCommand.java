package nixy.command;

import nixy.ui.Ui;
import nixy.task.Task;
import nixy.task.TaskList;
import nixy.Storage;

/**
 * Class representing the command to add a task.
 * Works for all different types of tasks.
 */
public class AddTaskCommand implements Command {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private CommandType taskType;
    private Task task;

    /**
     * Constructor for AddTaskCommand.
     *
     * @param ui The Ui object to interact with the user.
     * @param tasks The TaskList object to store tasks.
     * @param storage The Storage object to store tasks data.
     * @param taskType The type of task to add.
     * @param task The task to add.
     */
    public AddTaskCommand(Ui ui, TaskList tasks, Storage storage, CommandType taskType, Task task) {
        this.ui = ui;
        this.tasks = tasks;
        this.storage = storage;
        this.taskType = taskType;
        this.task = task;
    }

    /**
     * Returns the type of command.
     *
     * @return CommandType.TODO, CommandType.DEADLINE or CommandType.EVENT
     */
    @Override
    public CommandType getType() {
        return taskType;
    }

    /**
     * Executes the command to add a task.
     */
    @Override
    public void execute() {
        tasks.addTask(task);
        ui.showAddedTask(task, tasks.getTaskCount());
        storage.save(tasks);
    }
}
