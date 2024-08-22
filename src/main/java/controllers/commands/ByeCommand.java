package controllers.commands;

import models.TaskList;

public class ByeCommand implements Command{

    @Override
    public void execute(TaskList taskList) {
        System.out.println(
                " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n");
        System.exit(0);
    }
}
