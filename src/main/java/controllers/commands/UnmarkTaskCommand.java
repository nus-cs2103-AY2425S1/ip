package controllers.commands;

import models.TaskList;

public class UnmarkTaskCommand implements Command {
    private int index;

    public UnmarkTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList) {
        try {
            taskList.unmarkTask(index-1);
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(taskList.getTask(this.index-1));
            System.out.println("____________________________________________________________");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("index out of bounds");
        }

    }
}
