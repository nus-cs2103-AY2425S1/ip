package shnoop.command;

import shnoop.exceptions.*;
import shnoop.storage.Storage;
import shnoop.tasks.*;
import shnoop.ui.*;

import java.io.IOException;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, ShnoopException {
        if (idx >= tasks.size() || tasks.size() == 0 || idx <= -1) {
            throw new ShnoopException("✿ Shnoop ✿: Don't break your neck tryna creep a little sneak mark"
                    + ", there's no task with that number.");
        }
        Task temp = tasks.get(idx);
        if (mode) {
            ui.mark(tasks.mark(idx), temp);
        } else {
            ui.unmark(tasks.unmark(idx), temp);
        }
        storage.save(tasks);
    }
}
