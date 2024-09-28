package chatbot.command;

import chatbot.bot.Storage;
import chatbot.bot.TaskList;
import chatbot.bot.Ui;
import chatbot.task.Task;

/** MarkCommand is a subclass of Command to mark tasks as completed */
public class MarkCommand extends Command {
    private final int index;
    public MarkCommand(int i) {
        this.index = i;
    }

    /** Marks the tasks as completed at the specified index in taskList, otherwise do nothing */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String output = "";
        if (this.index > taskList.size() || this.index < 0) {
            output = "index out of range";
            return output;
        }
        Task t = taskList.getTask(this.index).markAsDone();
        output = "I've marked as done:\n" + t;
        return output;
    }
}
