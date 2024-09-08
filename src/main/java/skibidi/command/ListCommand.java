package skibidi.command;

import java.util.Optional;

import skibidi.Storage;
import skibidi.TaskList;
import skibidi.Ui;

/**
 * Command to list all items in current task list.
 */
public class ListCommand extends AbstractCommand {
    /**
     * Execute list command and return string representing items to be printed.
     */
    public Optional<String> execute(TaskList taskList, Storage storage, Ui ui) {
        if (taskList.isEmpty()) {
            return Optional.of("NO ITEMS");
        }
        String message = "LISTING ITEMS:\n" + taskList.toString();
        return Optional.of(message);
    }
}
