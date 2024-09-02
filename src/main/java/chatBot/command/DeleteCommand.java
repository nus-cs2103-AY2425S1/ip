package chatBot.command;

import chatBot.bot.Storage;
import chatBot.bot.TaskList;
import chatBot.bot.Ui;

public class DeleteCommand extends Command {
    private int numToDelete;
    public DeleteCommand(int i) {
        this.numToDelete = i;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (numToDelete > taskList.size()) {
            System.out.println("index out of bounds");
            return;
        }
        System.out.println("deleted: " + taskList.getTaskToString(numToDelete - 1));
        taskList.removeTask(numToDelete - 1);
    }
}
