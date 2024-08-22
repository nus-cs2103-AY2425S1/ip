package controllers.commands;

import models.TaskList;

public class ListCommand implements Command{

    @Override
    public void execute(TaskList taskList) {
        System.out.println(taskList.listTasks());
    }
}
