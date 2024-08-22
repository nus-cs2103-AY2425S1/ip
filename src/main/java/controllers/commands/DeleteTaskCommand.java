package controllers.commands;

import models.Task;
import models.TaskList;

public class DeleteTaskCommand implements Command {
    private int index;

    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList) {
        Task task = taskList.removeTask(this.index);
        System.out.println("____________________________________________________________\n" +
                "Removed: " + task.getDescription() + "\n" +
                "____________________________________________________________");
    }
}

