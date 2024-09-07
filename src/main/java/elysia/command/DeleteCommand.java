package elysia.command;

import elysia.exception.EmptyDescriptionException;
import elysia.storage.Storage;
import elysia.task.Task;
import elysia.ui.Ui;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    int index;
    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Storage storage) throws EmptyDescriptionException {
        Task task = tasks.get(index);
        tasks.remove(index);

        Ui ui = new Ui();

        ui.showDeleteTaskMessage(tasks, task);
    }
}
