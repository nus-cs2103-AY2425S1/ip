package skibidi.command;

import java.util.Optional;

import skibidi.Storage;
import skibidi.TaskList;
import skibidi.Ui;

public class ListCommand extends AbstractCommand {
    public Optional<String> execute(TaskList taskList, Storage storage, Ui ui) {
        if (taskList.isEmpty()) {
            return Optional.of("NO ITEMS");
        }
        String message = "LISTING ITEMS:\n" + taskList.toString();
        return Optional.of(message);
    }
}
