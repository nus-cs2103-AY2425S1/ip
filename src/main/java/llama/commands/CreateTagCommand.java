package llama.commands;

import java.io.IOException;

import llama.data.Storage;
import llama.data.TagList;
import llama.data.TaskList;
import llama.exceptions.InvalidTagException;
import llama.ui.Ui;


/**
 * Represents a command to create a new tag
 */
public class CreateTagCommand implements Command {
    private String remaining;

    /**
     * Creates a command to create a new tag
     *
     * @param remaining input representing the title of tag
     */
    public CreateTagCommand(String remaining) {
        this.remaining = remaining;
    }

    @Override
    public String execute(TaskList taskList, TagList tagList, Ui ui, Storage storage) throws IOException {
        String response = "";
        try {
            response += tagList.createTag(this.remaining, ui);
        } catch (InvalidTagException e) {
            response = ui.displayString(e.getMessage());
        }

        storage.saveTags(tagList);
        return response;
    }
}
