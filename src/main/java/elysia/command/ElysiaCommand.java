package elysia.command;

import java.io.IOException;
import java.util.ArrayList;

import elysia.exception.EmptyDescriptionException;
import elysia.task.Task;

/**
 * Represents a command that the name of Elysia being called.
 */
public class ElysiaCommand extends Command {

    /**
     * Responds to user if Elysia is called.
     */
    @Override
    public String execute(ArrayList<Task> tasks) throws EmptyDescriptionException, IOException {
        return "Why the sad face? Smile a little! Are you not happy being with me?";
    }
}
