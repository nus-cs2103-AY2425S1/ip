package chatbot.command;

import chatbot.bot.Storage;
import chatbot.bot.TaskList;
import chatbot.bot.Ui;
import chatbot.task.Task;

/** UnmarkCommand is a subclass of Command to mark tasks as not completed */
public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int i) {
        this.index = i;
    }

    /** Marks the tasks as incomplete at the specified index in taskList, otherwise do nothing */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String output = "";
        if (this.index > taskList.size()) {
            output = "index out of range";
            System.out.println(output);
            return output;
        }
        Task t = taskList.getTask(this.index).markAsNotDone();
        output = "I've marked as done:\n" + t;
        System.out.println(output);
        return output;
    }
}
