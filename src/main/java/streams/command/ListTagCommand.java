package streams.command;

import java.util.List;
import java.util.stream.Collectors;

import streams.task.Task;
import streams.task.TaskList;
import streams.util.Storage;
import streams.util.Ui;

/**
 * Represents a command to list all tasks with a specific tag.
 */
public class ListTagCommand extends Command {
    private String tag;

    /**
     * Constructs a ListTagCommand with the given tag.
     *
     * @param tag The tag to search for.
     */
    public ListTagCommand(String tag) {
        assert tag != null : "Tag should not be null";
        this.tag = tag.trim().toLowerCase();
    }

    /**
     * Executes the list tag command.
     *
     * @param tasks The task list to search in.
     * @param ui The user interface to display messages.
     * @param storage The storage (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "Tasks should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        List<Task> taggedTasks = tasks.getTasks().stream()
                .filter(task -> task.hasTag(tag))
                .collect(Collectors.toList());

        if (taggedTasks.isEmpty()) {
            ui.showMessage("No tasks found with tag '" + tag + "'");
        } else {
            ui.showMessage("Tasks with tag '" + tag + "':");
            for (int i = 0; i < taggedTasks.size(); i++) {
                ui.showMessage((i + 1) + ". " + taggedTasks.get(i));
            }
        }
    }
}
