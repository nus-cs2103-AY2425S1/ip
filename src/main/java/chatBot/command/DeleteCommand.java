package chatbot.command;

import chatbot.bot.Storage;
import chatbot.bot.TaskList;
import chatbot.bot.Ui;

/** DeleteCommand is a subclass of Command to delete tasks */
public class DeleteCommand extends Command {
    private int numToDelete;
    public DeleteCommand(int i) {
        this.numToDelete = i;
    }

    /** Deletes the task at specified index in taskList, if unsuccessful, do nothing */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String output = "";
        if (numToDelete > taskList.size() || numToDelete < 0) {
            output = "index out of bounds";
            return output;
        }
        output = "deleted: " + taskList.getTaskToString(numToDelete);
        taskList.removeTask(numToDelete);
        return output;
    }
}
