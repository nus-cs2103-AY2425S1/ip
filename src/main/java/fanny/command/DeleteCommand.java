package fanny.command;

import fanny.task.TaskList;
import fanny.ui.Ui;

public class DeleteCommand extends Command {
    private String arguments;

    public DeleteCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void actionable(TaskList list, Ui ui) {
        ui.showHorizontalLine();
        int taskId = Integer.parseInt(arguments);
        ui.showMessage("Fanny:\nNoted. I've removed this task:");
        ui.showMessage(list.delete(taskId));
        ui.showMessage("Now you have " + list.getLength() + " tasks in the list.");
        ui.showHorizontalLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }

}