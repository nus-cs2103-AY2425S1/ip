package elster.commands;

import elster.Elseption;
import elster.Storage;
import elster.TaskList;
import elster.Ui;

/**
 * UntagCommand class to represent a command that is executed when the user untags a task.
 */
public class UntagCommand extends Command {
    private UntagCommand(Ui ui, TaskList tasklist, Storage storage, String input) {
        super(ui, tasklist, storage, input);
    }

    public static UntagCommand of(Ui ui, TaskList tasklist, Storage storage, String input) {
        return new UntagCommand(ui, tasklist, storage, input);
    }
    @Override
    public String execute() {
        try {
            if (!input.contains("/tag")) {
                throw new Elseption("Please give the tag a tag name with /tag.");
            }

            int tagIndex = input.indexOf("/tag");
            int index = Integer.parseInt(input.substring(4, tagIndex).strip());

            String tagStr = input.substring(tagIndex + 5).strip();

            tasklist.tagTask(index, tagStr);
            storage.writeToFile(tasklist);
            return ui.tagTaskMessage(tasklist.getTask(index));

        } catch (Elseption e) {
            return ui.printErrorMessage(e.getMessage());
        }
    }
}
