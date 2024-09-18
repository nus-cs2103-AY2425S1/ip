package llama.commands;

import java.io.IOException;

import llama.data.Storage;
import llama.data.Tag;
import llama.data.TagList;
import llama.data.TaskList;
import llama.exceptions.InvalidTagException;
import llama.exceptions.LlamaException;
import llama.ui.Ui;

/**
 * Represents the command to tag a task
 */
public class TagTaskCommand implements Command {
    private String remaining;

    /**
     * Creates a command to tag tasks
     * @param remaining input that represents the task and tag
     */
    public TagTaskCommand(String remaining) {
        if (remaining.isBlank()) {
            throw new LlamaException("Empty tag/task?!? What are you asking me to tag?");
        }
        this.remaining = remaining;
    }

    @Override
    public String execute(TaskList taskList, TagList tagList, Ui ui, Storage storage) throws IOException {
        if (remaining.isBlank()) {
            throw new LlamaException("Empty tag/task?!? What are you asking me to tag?");
        }

        String response = "";
        String[] substringArr = remaining.split("/with ");
        int taskIndex = -1;
        try {
            taskIndex = Integer.parseInt(substringArr[0].strip()) - 1;
        } catch (NumberFormatException e) {
            throw new LlamaException("Invalid input!!! Please enter a valid task number to tag in this format: \n"
                    + "tag <task number> /with <tag title>");
        }
        String tagTitle = substringArr[1];
        if (tagTitle.isBlank()) {
            throw new LlamaException("Empty tag?!? What are you asking me to tag?");
        }

        try {
            Tag tag = tagList.getTagByTitle(tagTitle);
            taskList.tagTask(taskIndex, tag);
            response += ui.displayString("Task successfully tagged!");
        } catch (InvalidTagException e) {
            response = ui.displayString(e.getMessage());
            return response;
        } catch (IndexOutOfBoundsException e) {
            response = ui.displayString("Task does not exist!");
            return response;
        }

        storage.saveTasks(taskList);
        return response;
    }
}
