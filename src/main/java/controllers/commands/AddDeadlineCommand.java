package controllers.commands;

import models.Task;
import models.TaskList;

public class AddDeadlineCommand implements Command {
    private Task task;

    public AddDeadlineCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList) {
        taskList.addTask(this.task);
        System.out.println("____________________________________________________________\n" +
                "added: " + this.task.getDescription() + "\n" +
                "____________________________________________________________\n");
    }
}
