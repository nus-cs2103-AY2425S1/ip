package fanny.command;

import fanny.task.TaskList;
import fanny.ui.Ui;

public class MarkCommand extends Command {
    private String arguments;

    public MarkCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void actionable(TaskList list, Ui ui) {
        ui.showHorizontalLine();
        int taskId = Integer.parseInt(arguments);
        ui.showMessage("Fanny:\nNice! I've marked this task as done:");
        ui.showMessage(list.markAsDone(taskId - 1));
        ui.showHorizontalLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
