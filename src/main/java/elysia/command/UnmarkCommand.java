package elysia.command;

import java.io.IOException;
import java.util.ArrayList;

import elysia.storage.Storage;
import elysia.task.Task;
import elysia.ui.Ui;

/**
 * Represents a command that marks an item as has not been completed.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs a {@code UnmarkCommand} with the specified 1-indexed positon.
     *
     * @param index
     */
    public UnmarkCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Marks the item as [ ].
     *
     * @param tasks
     * @return
     */
    @Override
    public String execute(ArrayList<Task> tasks) {
        Task curr = tasks.get(this.index);
        curr.unmarkAsDone();

        Ui ui = new Ui();
        try {
            Storage storage = new Storage(tasks);
            storage.saveFile();
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }
        return ui.getUnmarkAsDoneMessage(curr);
    }
}
