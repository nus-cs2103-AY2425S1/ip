package lunabot.command;

import lunabot.exception.LunaBotException;
import lunabot.storage.Storage;
import lunabot.task.Task;
import lunabot.task.TaskList;
import lunabot.ui.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(String input) throws LunaBotException {
        try {
            // extracts index as a string and converts to an int
            this.index = Integer.parseInt(input.substring(7).trim()) - 1;
        }
        catch (NumberFormatException e) {
            // checks if user inout an int
            throw new LunaBotException(" Invalid task number format");
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws LunaBotException {
        // Checks if task number provided is valid and within range
        if (index < 0 || index >= taskList.size()) {
            throw new LunaBotException("Invalid task number provided");
        }
        Task deleted = taskList.deleteTask(index);
        storage.save(taskList.getTasks());
        ui.printTaskDeleted(deleted, taskList.size());
    }
}
