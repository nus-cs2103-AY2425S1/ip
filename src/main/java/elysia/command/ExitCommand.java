package elysia.command;

import elysia.exception.EmptyDescriptionException;
import elysia.storage.Storage;
import elysia.task.Task;
import elysia.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;


public class ExitCommand extends Command {
    public ExitCommand() {

    }

    @Override
    public String execute(ArrayList<Task> tasks) throws EmptyDescriptionException {
        this.hasExited = true;
        Ui ui = new Ui();
        try {
            Storage storage = new Storage(tasks);
            storage.saveFile();
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }
        return ui.getExitMessage();
    }
}
