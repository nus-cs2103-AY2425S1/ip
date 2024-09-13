package gumbo.commands;

import gumbo.storage.Storage;
import gumbo.tasks.Task;
import gumbo.tasks.TaskList;
import gumbo.ui.Ui;

public class MarkCommand extends Command {
    private int taskNumToMark;
    public MarkCommand(int taskNum) {
        this.taskNumToMark = taskNum;
    }

    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) {
        Task markedTask = taskList.get(taskNumToMark);
        markedTask.markAsDone();
        return "Nice! I've marked this task as done:\n"
                + markedTask;
    }
}
