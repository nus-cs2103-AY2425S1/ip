package bimo.command;

import bimo.Storage;
import bimo.TaskList;
import bimo.Ui;
import bimo.tasks.Task;

/**
 * Creates a command that add tasks to list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Instantiates a command that adds user task.
     *
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds specified task to user list.
     *
     * @param tasks List of user tasks.
     * @param ui User interface that interacts with users.
     * @param storage Storage that writes and load files.
     * @return Response of chatbot.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert task != null : "Task must not be null";
        assert tasks != null : "Task list must not be null";
        tasks.addTask(task);
        String word = tasks.getLength() == 1 ? "task" : "tasks";
        storage.appendToFile(task);
        return "Got it. I've added this task:\n" + "    " + task.toString()
                + "\n" + String.format("Now you have %d %s in the tasks.",
                tasks.getLength(), word);
    }
}
