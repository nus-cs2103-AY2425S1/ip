package shnoop.command;

import java.io.IOException;

import shnoop.exceptions.ShnoopException;
import shnoop.storage.Storage;
import shnoop.tasks.Task;
import shnoop.tasks.TaskList;
import shnoop.ui.Ui;


/**
 * Encapsulates all the relevant actions to be taken when a Delete Command is issued.
 */
public class DeleteCommand extends Command {
    private int idx;

    /**
     * Creates an instance of a DeleteCommand to be executed after.
     *
     * @param input String command to be read.
     * @throws ShnoopException If input was invalid and a number was not provided after the Command keyword.
     */
    public DeleteCommand(String input) throws ShnoopException {
        try {
            idx = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (NumberFormatException e) {
            throw new ShnoopException("✿ Shnoop ✿: It's like saying you wanna eat my soda, "
                    + " give me a number after a command g.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, ShnoopException {
        if (idx >= tasks.size() || tasks.size() == 0 || idx <= -1) {
            throw new ShnoopException("✿ Shnoop ✿: Don't break your neck tryna creep a little sneak mark"
                    + ", there's no task with that number.");
        }
        Task temp = tasks.get(idx);
        ui.delete(tasks.delete(idx));
    }
}
