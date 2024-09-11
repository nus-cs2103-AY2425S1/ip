package elysia.command;

import elysia.exception.EmptyDescriptionException;
import elysia.storage.Storage;
import elysia.task.Task;
import elysia.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteCommand extends Command {
    int index;
    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public String execute(ArrayList<Task> tasks) throws EmptyDescriptionException {
        Task task = tasks.get(index);
        assert task != null: "task is null";
        tasks.remove(index);

        Ui ui = new Ui();
        try {
            Storage storage = new Storage(tasks);
            storage.saveFile();
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }

        return ui.getDeleteTaskMessage(tasks, task);
    }
}
