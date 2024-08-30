package thebotfather.command;

import thebotfather.task.Task;
import thebotfather.util.Storage;
import thebotfather.util.TaskList;
import thebotfather.util.TheBotFatherException;
import thebotfather.util.Ui;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(String stringIndex) throws TheBotFatherException {
        try {
            this.index = Integer.parseInt(stringIndex) - 1;
        } catch (NumberFormatException e) {
            throw new TheBotFatherException("How do you not know what a number is, jeez\n" +
                    "\tTo delete a task enter \"delete <index>\"");
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TheBotFatherException {
        Task deletedTask = taskList.delete(index);
        storage.toFile(taskList);
        ui.printDeleted(deletedTask);
    }
}
