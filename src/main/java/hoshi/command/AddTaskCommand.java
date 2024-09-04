package hoshi.command;

import hoshi.task.TaskList;
import hoshi.ui.Ui;

public class AddTaskCommand extends Command {

    private TaskList tasks;
    private Ui ui;
    private String taskType;

    /**
     * Constructor for AddTaskCommand.
     * @param tasks    the TaskList that stores 3 types of tasks.
     * @param ui       Ui that handles displaying information to user.
     * @param taskType the type of task to be added.
     */
    public AddTaskCommand(TaskList tasks, Ui ui, String taskType) {
        this.tasks = tasks;
        this.ui = ui;
        this.taskType = taskType;
    }

    @Override
    public void execute() {

    }
}
