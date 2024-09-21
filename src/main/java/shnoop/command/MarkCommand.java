package shnoop.command;

import java.io.IOException;

import shnoop.exceptions.ShnoopException;
import shnoop.storage.Storage;
import shnoop.tasks.Task;
import shnoop.tasks.TaskList;
import shnoop.ui.Ui;

/**
 * Encapsulates all the relevant actions to be taken when a Mark Command is issued.
 */
public class MarkCommand extends Command {
    private int idx;
    private boolean mode;

    /**
     * Creates an instance of a MarkCommand to be executed after.
     *
     * @param input String input to be read.
     * @param mode True if the command indicates a Mark Command, false if the command indicates an Unmark Command.
     */
    public MarkCommand(String input, boolean mode) {
        idx = Integer.parseInt(input.split(" ")[1]) - 1;
        this.mode = mode;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, ShnoopException {
        if (idx >= tasks.size() || tasks.size() == 0 || idx <= -1) {
            throw new ShnoopException("✿ Shnoop ✿: Don't break your neck tryna creep a little sneak mark"
                    + ", there's no task with that number.");
        }
        Task temp = tasks.get(idx);
        String output = "";
        if (mode) {
            output += ui.mark(tasks.mark(idx), temp);
        } else {
            output += ui.unmark(tasks.unmark(idx), temp);
        }
        storage.save(tasks);
        return output;
    }
}
