package elysia.command;

import java.io.IOException;
import java.util.ArrayList;

import elysia.storage.Storage;
import elysia.task.Task;
import elysia.ui.Ui;

/**
 * Represents a command that marks an item as done.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a {@code MarkCommand} with the specified 1-indexed index.
     *
     * @param index
     */
    public MarkCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Marks the item in the list as [x].
     *
     * @param tasks
     * @return
     */
    @Override
    public String execute(ArrayList<Task> tasks) {
        Task curr = tasks.get(this.index);
        curr.markAsDone();

        Ui ui = new Ui();
        try {
            Storage storage = new Storage(tasks);
            storage.saveFile();
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }
        return ui.getMarkAsDoneMessage(curr);
    }
}
