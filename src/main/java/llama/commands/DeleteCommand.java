package llama.commands;

import java.io.IOException;

import llama.data.Storage;
import llama.data.TagList;
import llama.data.TaskList;
import llama.exceptions.InvalidTaskException;
import llama.ui.Ui;

/**
 * Represents the command to delete a Task
 */
public class DeleteCommand implements Command {
    private String remaining;

    /**
     * Creates a command to delete task
     *
     * @param remaining input to tell program which task to delete
     */
    public DeleteCommand(String remaining) {
        this.remaining = remaining;
    }

    @Override
    public String execute(TaskList taskList, TagList tagList, Ui ui, Storage storage) throws IOException {
        String response = "";
        int index = Integer.parseInt(remaining);
        try {
            response = taskList.deleteTask(index, ui);
            storage.saveTasks(taskList);
        } catch (InvalidTaskException e) {
            response = ui.displayString(e.getMessage());
        }
        return response;
    }
}
