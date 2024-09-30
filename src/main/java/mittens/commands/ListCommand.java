package mittens.commands;

import mittens.parser.RawCommandElements;
import mittens.storage.Storage;
import mittens.task.Task;
import mittens.task.TaskList;
import mittens.ui.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command for listing all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Creates a new ListCommand object with the specified command elements.
     * It assumes the command name corresponds with this class.
     *
     * @param elements The RawCommandElements object
     */
    public ListCommand(RawCommandElements elements) {
        assert elements.getCommand().equals("list") : "Command name should be matching";
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<String> messages = new ArrayList<>();

        int count = tasks.getCount();
        if (count == 0) {
            messages.add("Meow?! Your list is empty!");
        } else {
            messages.add("You have %d tasks in your list, here they are :3".formatted(count));
        }

        for (int i = 0; i < count; i++) {
            Task task = tasks.getTask(i);
            messages.add("%d. %s".formatted(i + 1, task));
        }

        ui.showRegularMessage(messages);
    }
}
