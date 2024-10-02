package moimoi.util.command;

import moimoi.util.Storage;
import moimoi.util.TaskList;
import moimoi.util.task.Task;

/**
 * Represents a command to filter the task list, by a specific description keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs a command to filter the task list, by the specified description keyword.
     *
     * @param keyword Keyword to be checked within tasks' descriptions.
     */
    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    /**
     * Executes task filtering, by description keyword.
     *
     * @param storage MoiMoi's storage.
     * @param tasks List of existing tasks.
     * @return Completion message.
     */
    @Override
    public String execute(Storage storage, TaskList tasks) {

        StringBuilder completionMessage = new StringBuilder("Here's your list of tasks, containing '"
                + this.keyword + "' in their descriptions!");
        boolean isEmpty = true;

        for (int i = 1; i <= tasks.getSize(); i = i + 1) {
            Task task = tasks.get(i);
            if (task.hasKeyword(this.keyword)) {
                isEmpty = false;
                completionMessage.append("\n").append(i).append(". ").append(task.stringUI());
            }
        }

        if (isEmpty) {
            completionMessage.append("\n(No such tasks found...)");
        }

        return completionMessage.toString();

    }

}
