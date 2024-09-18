package gavinchatbot.command;

import java.util.ArrayList;

import gavinchatbot.task.Task;
import gavinchatbot.task.TaskList;
import gavinchatbot.util.GavinException;
import gavinchatbot.util.Storage;
import gavinchatbot.util.Ui;

/**
 * Represents a command that adds a tag to a task in the task list.
 */
public class TagCommand implements Command {
    private final int index;
    private final String tag;

    /**
     * Constructs a TagCommand with the specified task index and tag.
     *
     * @param index The index of the task to tag (0-based).
     * @param tag The tag to be added to the task.
     */
    public TagCommand(int index, String tag) {
        this.index = index;
        this.tag = tag;
    }

    /**
     * Executes the tag command, adding the specified tag to the task at the given index.
     *
     * @param tasks The task list where the task is located.
     * @param ui The UI to display messages to the user.
     * @param storage The storage where the task list is saved (not used in this command).
     * @return A message indicating the result of adding the tag to the task.
     * @throws GavinException If there is an error retrieving the task
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GavinException {
        Task task = tasks.getTask(index);
        task.addTag(tag);

        // Create a list containing the single tagged task
        ArrayList<Task> taggedTaskList = new ArrayList<>();
        taggedTaskList.add(task);

        // Show the tagged task using the UI
        return ui.showTaggedMessage(taggedTaskList, tag);
    }

    /**
     * Indicates whether this command will cause the application to exit.
     *
     * @return false as tagging a task does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
