package elysia.command;

import elysia.exception.EmptyDescriptionException;
import elysia.storage.Storage;
import elysia.task.Task;
import elysia.ui.Ui;

import java.util.ArrayList;

public class PrintListCommand extends Command{
    public PrintListCommand () {
        super();
    }

    @Override
    public void execute(ArrayList<Task> tasks, Storage storage) throws EmptyDescriptionException {
        Ui ui = new Ui();
        ui.printList(tasks);
    }
}
