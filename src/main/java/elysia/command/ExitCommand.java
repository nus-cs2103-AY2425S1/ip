package elysia.command;

import elysia.exception.EmptyDescriptionException;
import elysia.storage.Storage;
import elysia.task.Task;

import java.util.ArrayList;


public class ExitCommand extends Command {
    public ExitCommand () {

    }

    @Override
    public void execute(ArrayList<Task> tasks, Storage storage) throws EmptyDescriptionException {
        this.hasExited = true;
    }
}
