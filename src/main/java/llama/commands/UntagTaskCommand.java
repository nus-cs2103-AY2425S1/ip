package llama.commands;

import java.io.IOException;

import llama.data.Storage;
import llama.data.TagList;
import llama.data.Task;
import llama.data.TaskList;
import llama.exceptions.LlamaException;
import llama.ui.Ui;

/**
 * Represents the command to untag a task
 */
public class UntagTaskCommand implements Command {
    private String remaining;

    /**
     * Creates a command to untag a task
     * @param remaining
     */
    public UntagTaskCommand(String remaining) {
        this.remaining = remaining;
    }

    @Override
    public String execute(TaskList taskList, TagList tagList, Ui ui, Storage storage) throws IOException {
        if (remaining.isBlank()) {
            throw new LlamaException("Empty task number?!? What are you asking me to untag?");
        }

        String response = "";
        int taskIndex = -1;
        try {
            taskIndex = Integer.parseInt(this.remaining.strip()) - 1;
        } catch (NumberFormatException e) {
            throw new LlamaException("Invalid input!!! Please enter a valid task "
                    + "number to untag in this format: \nuntag <task number>");
        }

        try {
            Task task = taskList.getTask(taskIndex);
            task.removeTag();
        } catch (IndexOutOfBoundsException e) {
            response = ui.displayString("Task does not exist!");
            return response;
        }

        response += ui.displayString("Task successfully untagged!");
        storage.saveTasks(taskList);
        return response;
    }
}
