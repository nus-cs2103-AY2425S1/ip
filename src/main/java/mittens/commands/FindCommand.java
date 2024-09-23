package mittens.commands;

import java.util.ArrayList;
import java.util.List;

import mittens.parser.BadInputException;
import mittens.parser.RawCommandElements;
import mittens.storage.Storage;
import mittens.task.Task;
import mittens.task.TaskList;
import mittens.ui.Ui;

/**
 * Represents a command for finding tasks that match a keyword.
 */
public class FindCommand extends Command {
    protected String keyword;

    /**
     * Creates a new FindCommand object with the specified command elements.
     * It assumes the command name corresponds with this class.
     *
     * @param elements The RawCommandElements object
     * @throws BadInputException If the input is invalid
     */
    public FindCommand(RawCommandElements elements) throws BadInputException {
        assert elements.getCommand().equals("find") : "Command name should be matching";
        this.keyword = elements.getArgumentOrThrow();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = tasks.findTasks(this.keyword);
        List<String> messages = new ArrayList<>();

        int count = matchingTasks.size();
        if (count == 0) {
            messages.add("Meow?! I couldn't find anything with that keyword!");
        } else {
            messages.add("I found %d tasks with that keyword, here they are :3".formatted(count));
        }

        for (int i = 0; i < count; i++) {
            Task task = matchingTasks.get(i);
            messages.add("%d. %s".formatted(i + 1, task));
        }

        ui.showRegularMessage(messages);
    }
}
