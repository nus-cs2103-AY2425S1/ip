package elysia.command;

import elysia.exception.EmptyDescriptionException;
import elysia.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class ElysiaCommand extends Command {
    @Override
    public String execute(ArrayList<Task> tasks) throws EmptyDescriptionException, IOException {
        return "Why the sad face? Smile a little! Are you not happy being with me?";
    }
}
