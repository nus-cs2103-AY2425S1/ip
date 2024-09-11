package llama.commands;

import java.io.IOException;

import llama.data.Storage;
import llama.data.TagList;
import llama.data.Task;
import llama.data.TaskList;
import llama.ui.Ui;

/**
 * Represents the command to untag a task
 */
public class UntagTaskCommand implements Command {
    private String remaining;

    public UntagTaskCommand(String remaining) {
        this.remaining = remaining;
    }

    @Override
    public String execute(TaskList taskList, TagList tagList, Ui ui, Storage storage) throws IOException {
        String response = "";
        int taskIndex = Integer.parseInt(this.remaining.strip()) - 1;
        Task task = taskList.getTask(taskIndex);
        task.removeTag();
        response += ui.displayString("Task successfully untagged!");
        storage.saveTasks(taskList);
        return response;
    }
}
