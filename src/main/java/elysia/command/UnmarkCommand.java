package elysia.command;

import elysia.exception.EmptyDescriptionException;
import elysia.storage.Storage;
import elysia.task.Task;
import elysia.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class UnmarkCommand extends Command {
    int index;
    public UnmarkCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public String execute(ArrayList<Task> tasks, Storage storage) throws EmptyDescriptionException {
        Task curr = tasks.get(this.index);
        curr.unmarkAsDone();

        Ui ui = new Ui();
        try {
            storage.saveFile();
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }
        return ui.getUnmarkAsDoneMessage(curr);
    }
}
