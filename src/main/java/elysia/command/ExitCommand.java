package elysia.command;

import java.io.IOException;
import java.util.ArrayList;

import elysia.storage.Storage;
import elysia.task.Task;
import elysia.ui.Ui;

/**
 * Represents an exit command.
 */
public class ExitCommand extends Command {
    public ExitCommand() {

    }

    /**
     * Says goodbye to user when user exits. Saves the content in lists before end.
     *
     * @param tasks
     * @return
     */
    @Override
    public String execute(ArrayList<Task> tasks) {
        Ui ui = new Ui();
        try {
            Storage storage = new Storage(tasks);
            storage.saveFile();
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }
        return ui.getExitMessage();
    }

    /**
     * Tells the program to close the application.
     *
     * @return
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
