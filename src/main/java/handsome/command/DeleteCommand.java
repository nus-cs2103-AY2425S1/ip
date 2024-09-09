package handsome.command;

import java.io.IOException;

import handsome.Storage;
import handsome.TaskList;
import handsome.Ui;
import handsome.exception.HandsomeException;

/**
 * The DeleteCommand class handles the "delete" command, which removes
 * a task from the Handsome chatBot's task list based on the user's specified index.
 */
public class DeleteCommand extends Command {
    private final String input;

    /**
     * Constructs a DeleteCommand object by storing the user input that
     * specifies which task to delete.
     *
     * @param input The input string containing the "delete" command and the task index.
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws HandsomeException, IOException {
        int index = toNumber(input.substring(7), tasks.getSize()) - 1;
        tasks.remove(index);
        storage.writeToFile(tasks);
        return ui.showDeleteText(index, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
