package shnoop.command;

import shnoop.exceptions.*;
import shnoop.storage.Storage;
import shnoop.tasks.*;
import shnoop.ui.*;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int idx;

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
