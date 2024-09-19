package neuro.command;

import neuro.Storage;
import neuro.Ui;
import neuro.task.Task;
import neuro.task.TaskList;

/**
 * The {@code TagCommand} class represents a command to tag a task in the task list.
 */
public class TagCommand extends Command {
    private final int index;
    private final String[] commandComponents;

    /**
     * Constructs a TagCommand object with the specified index and command components.
     * The index is used to determine which task to add the tags to.
     * The command components consist of the different tags to add, separated by spaces
     * in the user's input.
     *
     * @param index The index of the task to tag from the task list.
     * @param commandComponents An array of strings representing the components of the command
     */
    public TagCommand(int index, String[] commandComponents) {
        this.index = index;
        this.commandComponents = commandComponents;
    }

    /**
     * Executes the tag command to tag a task in the task list.
     *
     * @param tasks the task list on which the command operates
     * @param ui the user interface for interacting with the user
     * @param storage the storage for saving and loading tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.index < 0 || this.index > tasks.getSize()) {
            return "Oh no~! Missing or invalid index for 'tag' command! Add a valid "
                    + "index for a task to tag, like 'tag 2 fun'.";
        }

        assert index - 1 < tasks.getSize() : "Index should be within size of task list";

        Task task = tasks.getTask(index - 1);
        for (int i = 2; i < commandComponents.length; i++) {
            String tag = commandComponents[i];
            task.addTag(tag);
        }
        storage.updateTaskFile(tasks);

        return "Ok~! I've added the tags to this task:\n"
                + "     " + task;
    }
}
