package elysia.command;

import java.io.IOException;
import java.util.ArrayList;

import elysia.storage.Storage;
import elysia.task.Task;
import elysia.ui.Ui;

/**
 * Removes all tasks in the list.
 */
public class ClearCommand extends Command {
    private int index;

    public ClearCommand() {
        super();
    }

    /**
     * Removes all tasks in the list.
     *
     * @param tasks
     * @return
     */
    @Override
    public String execute(ArrayList<Task> tasks) {
        for (int i = tasks.size() - 1; i >= 0; i--) {
            tasks.remove(i);
        }

        Ui ui = new Ui();
        try {
            Storage storage = new Storage(tasks);
            storage.saveFile();
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }
        return ui.getClearTasksMessage();
    }
}
