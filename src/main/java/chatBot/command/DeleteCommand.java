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
        if (numToDelete > taskList.size()) {
            output = "index out of bounds";
            System.out.println(output);
            return output;
        }
        output = "deleted: " + taskList.getTaskToString(numToDelete - 1);
        System.out.println(output);
        taskList.removeTask(numToDelete - 1);
        return output;
    }
}
