package controllers.commands;

import models.Task;
import models.Todo;
import models.TaskList;

public class AddTodoCommand implements Command
{
    private Todo task;

    public AddTodoCommand(Todo task) {
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
