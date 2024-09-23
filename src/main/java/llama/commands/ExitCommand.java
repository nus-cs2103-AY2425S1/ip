package llama.commands;

import llama.data.Storage;
import llama.data.TagList;
import llama.data.TaskList;
import llama.ui.Ui;

/**
 * Represents the command to exit the program
 */
public class ExitCommand implements Command {
    @Override
    public String execute(TaskList taskList, TagList tagList, Ui ui, Storage storage) {
        return ui.displayBye();
    }
}
