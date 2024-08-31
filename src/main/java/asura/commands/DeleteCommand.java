package asura.commands;

import asura.data.exception.AsuraException;
import asura.data.tasks.Task;
import asura.data.tasks.TaskList;
import asura.storage.Storage;
import asura.ui.Ui;

public class DeleteCommand extends Command {
    int selection;

    public DeleteCommand(int selection) {
        this.selection = selection;
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) throws AsuraException {
        Task removedTask = tasklist.removeAt(selection);
        output.append("Noted! I've removed this task :").append("\n").append(removedTask.toString()).append("\n")
                .append("Now you have ").append(tasklist.size()).append(" tasks in your list.\n");
        storage.save(tasklist.getTaskList());
        ui.printString(output.toString());
    }

    public boolean isExit() {
        return false;
    }
}
