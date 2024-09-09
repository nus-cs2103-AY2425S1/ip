package handsome.command;

import java.io.IOException;

import handsome.Storage;
import handsome.TaskList;
import handsome.Ui;
import handsome.exception.HandsomeException;

/**
 * The MarkCommand class handles the "mark" command, which marks a task
 * as done in the Handsome chatBot's task list based on the user's specified index.
 */
public class MarkCommand extends Command {
    private final String input;

    /**
     * Constructs a MarkCommand object by storing the user input that specifies
     * which task to mark as done.
     *
     * @param input The input string containing the "mark" command and the task index.
     */
    public MarkCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws HandsomeException, IOException {
        int index = toNumber(input.substring(5), tasks.getSize()) - 1;
        tasks.markDone(index);
        storage.writeToFile(tasks);
        return ui.showMarkText(index, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
