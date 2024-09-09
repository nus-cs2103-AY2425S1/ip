package handsome.command;

import java.io.IOException;

import handsome.Storage;
import handsome.TaskList;
import handsome.Ui;
import handsome.exception.HandsomeException;

/**
 * The UnmarkCommand class handles the "unmark" command, which marks a task
 * as not done in the Handsome chatBot's task list based on the user's specified index.
 */
public class UnmarkCommand extends Command {
    private final String input;

    /**
     * Constructs a UnmarkCommand object by storing the user input that specifies
     * which task to mark as undone.
     *
     * @param input The input string containing the "unmark" command and the task index.
     */
    public UnmarkCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws HandsomeException, IOException {
        int index = toNumber(input.substring(7), tasks.getSize()) - 1;
        tasks.markUndone(index);
        storage.writeToFile(tasks);
        return ui.showUnmarkText(index, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
