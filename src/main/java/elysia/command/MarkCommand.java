package elysia.command;

import elysia.exception.EmptyDescriptionException;
import elysia.storage.Storage;
import elysia.task.Task;
import elysia.ui.Ui;

import java.util.ArrayList;

public class MarkCommand extends Command {
    int index;
    public MarkCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Storage storage) throws EmptyDescriptionException {
        Task curr = tasks.get(this.index);
        curr.markAsDone();

        Ui ui = new Ui();
        ui.showMarkAsDoneMessage(curr);
    }
}
