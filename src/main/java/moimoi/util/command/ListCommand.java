package moimoi.util.command;

import moimoi.util.Storage;
import moimoi.util.TaskList;
import moimoi.util.task.Task;

/**
 * Represents a command to list all existing tasks.
 */
public class ListCommand extends Command {

    /**
     * Constructs a command to list all existing tasks.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Lists all existing tasks.
     *
     * @param storage MoiMoi's storage.
     * @param tasks List of existing tasks.
     * @return Completion message.
     */
    @Override
    public String execute(Storage storage, TaskList tasks) {

        StringBuilder completionMessage = new StringBuilder("Here's your list of tasks!");
        boolean isEmpty = true;

        for (int i = 1; i <= tasks.getSize(); i = i + 1) {
            isEmpty = false;
            Task task = tasks.get(i);
            completionMessage.append("\n").append(i).append(". ").append(task.stringUI());
        }

        if (isEmpty) {
            completionMessage.append("\n(No tasks found...)");
        }

        return completionMessage.toString();

    }

}
