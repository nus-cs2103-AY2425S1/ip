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
     * Constructs a TagCommand object with the TODO: Complete
     * @param commandComponents
     */
    public TagCommand(int index, String[] commandComponents) {
        this.index = index;
        this.commandComponents = commandComponents;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.index < 0 || this.index > tasks.getSize()) {
            return "Missing or invalid index for 'tag' command! Add a valid "
                    + "index for a task to tag, like 'tag 2 fun'.";
        }

        assert index - 1 < tasks.getSize() : "Index should be within size of task list";

        Task task = tasks.getTask(index - 1);
        for (int i = 2; i < commandComponents.length; i++) {
            String tag = commandComponents[i];
            task.addTag(tag);
        }
        storage.updateTaskFile(tasks);

        return "Ok! I've added the tags to this task:\n"
                + "     " + task;
    }
}
