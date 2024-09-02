package chatBot.command;

import chatBot.bot.Storage;
import chatBot.bot.TaskList;
import chatBot.bot.Ui;
import chatBot.task.Task;

/** UnmarkCommand is a subclass of Command to mark tasks as not completed */
public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int i) {
        this.index = i;
    }

    /** Marks the tasks as incomplete at the specified index in taskList, otherwise do nothing */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (this.index > taskList.size()) {
            System.out.println("index out of range");
            return;
        }
        Task t = taskList.getTask(this.index).markAsNotDone();
        System.out.println("I've marked as done:\n" + t);
    }
}