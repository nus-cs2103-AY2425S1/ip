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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (this.index > taskList.size()) {
            System.out.println("index out of range");
            return;
        }
        Task t = taskList.getTask(this.index).markAsDone();
        System.out.println("I've marked as done:\n" + t);
    }
}
