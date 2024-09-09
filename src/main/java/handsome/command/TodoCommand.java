package handsome.command;

import java.io.IOException;

import handsome.Storage;
import handsome.TaskList;
import handsome.Ui;
import handsome.exception.HandsomeException;
import handsome.task.ToDo;

/**
 * The TodoCommand class handles the "todo" command, which adds a new
 * ToDo task to the Handsome chatBot's task list.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Constructs a TodoCommand object by extracting the task description
     * from the user input.
     * The input must start with the keyword "todo", followed by a space
     * and the task description.
     *
     * @param input The input string containing the "todo" command and task description.
     */
    public TodoCommand(String input) {
        description = input.substring(5);
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws HandsomeException, IOException {
        tasks.add(new ToDo(description, "[ ]"));
        storage.writeToFile(tasks);
        return ui.showTaskText(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
