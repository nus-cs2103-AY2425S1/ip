package elster.commands;

import elster.Elseption;
import elster.Storage;
import elster.TaskList;
import elster.Ui;

/**
 * TagCommand class to represent a command that is executed when the user tags a task.
 */
public class TagCommand extends Command {
    private TagCommand(Ui ui, TaskList tasklist, Storage storage, String input) {
        super(ui, tasklist, storage, input);
    }

    public static TagCommand of(Ui ui, TaskList tasklist, Storage storage, String input) {
        return new TagCommand(ui, tasklist, storage, input);
    }
    @Override
    public String execute() {
        try {
            if (!input.contains("/tag")) {
                throw new Elseption("Please give the tag a tag name with /tag.");
            }

            int indexOfTag = input.indexOf("/tag");
            int listIndex = Integer.parseInt(input.substring(4, indexOfTag).strip());
            String tagStr = input.substring(indexOfTag + 5).strip();

            tasklist.tagTask(listIndex, tagStr);
            storage.writeToFile(tasklist);
            return ui.tagTaskMessage(tasklist.getTask(listIndex));

        } catch (Elseption e) {
            return ui.printErrorMessage(e.getMessage());
        }
    }
}
