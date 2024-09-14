package joe.commands;

import joe.tasks.TaskList;

/**
 * Represents a command to save the task list to the hard disk.
 */
public class SaveCommand extends Command {

    private final TaskList taskList;

    /**
     * Constructs a SaveCommand object.
     *
     * @param tasklist The task list to save.
     */
    public SaveCommand(TaskList tasklist) {
        this.taskList = tasklist;
    }

    @Override
    public String execute() {
        return taskList.saveTasks();
    }
}
