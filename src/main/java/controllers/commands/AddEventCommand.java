package controllers.commands;

import models.Task;
import models.Event;
import models.TaskList;

public class AddEventCommand implements Command {
    private Event task;

    public AddEventCommand(Event task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList) {
        taskList.addTask(this.task);
        System.out.println("____________________________________________________________\n" +
                "added: " + this.task.getDescription() + "\n" +
                "____________________________________________________________");
    }
}
