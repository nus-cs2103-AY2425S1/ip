package barney.action.commands;


import java.util.HashMap;

import barney.data.TaskList;
import barney.data.exception.InvalidArgumentException;
import barney.data.task.Task;
import barney.ui.Ui;

/**
 * Represents a command for tagging a task with a tag. Extends the
 * {@link Command} class.
 */
public class TagCommand extends Command {
    /**
     * Creates a new TagCommand object.
     *
     * @param argumentMap a HashMap containing the arguments for the command
     */
    public TagCommand(HashMap<String, String> argumentMap) {
        super("tag", argumentMap);
    }

    /**
     * Executes the TagCommand, tagging a task with a tag.
     *
     * @param tasks The TaskList containing the tasks.
     * @param ui    The Ui object for user interaction.
     * @return String representing the result of the tag task function.
     * @throws InvalidArgumentException if the task number is invalid or out of
     *                                  range.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) throws InvalidArgumentException {
        verifyFlags();

        String indexStr = getParameter("index");
        if (!indexStr.matches("^\\d+$")) {
            throw new InvalidArgumentException("Please enter a task number!");
        }
        int index = Integer.parseInt(indexStr) - 1;
        if (index < 0 || index > tasks.size() - 1) {
            throw new InvalidArgumentException("Task number out of range!");
        }

        Task task = tasks.get(index);
        task.setTag(getParameter("tag"));

        return ui.printTaggedTask(task);
    }
}
