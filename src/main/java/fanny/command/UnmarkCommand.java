package fanny.command;

import fanny.task.TaskList;
import fanny.ui.Ui;

public class UnmarkCommand extends Command {
    private String arguments;

    public UnmarkCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void actionable(TaskList list, Ui ui) {
        ui.showHorizontalLine();
        int taskId = Integer.parseInt(arguments);
        ui.showMessage("Fanny:\nOK, I've marked this task as not done yet:");
        ui.showMessage(list.markAsNotDone(taskId));
        ui.showHorizontalLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }

}