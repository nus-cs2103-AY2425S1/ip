package elysia.command;

import elysia.exception.EmptyDescriptionException;
import elysia.storage.Storage;
import elysia.task.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a command in the application.
 * Manages the task lists with commands.
 */
public abstract class Command {
    public boolean hasExited = false;

    public Command() {

    }


    /**
     * Executes the Command.
     */
    public abstract String execute(ArrayList<Task> tasks, Storage storage) throws EmptyDescriptionException, IOException;
}
