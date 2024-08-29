package commands;

import java.io.IOException;

import exceptions.InvalidTaskException;
import data.TaskList;
import data.Storage;
import ui.Ui;

/**
 * Represents the command to delete a Task
 */
public class DeleteCommand implements Command {
    private String remaining;

    /**
     * Constructor for DeleteCommand
     *
     * @param remaining input to tell program which task to delete
     */
    public DeleteCommand(String remaining) {
        this.remaining = remaining;
    }

    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        int index = Integer.parseInt(remaining);
        try {
            taskList.deleteTask(index, ui);
            storage.save(taskList);
        } catch (InvalidTaskException | IOException e) {
            ui.displayString(e.getMessage());
        }
        return true;
    }
}
