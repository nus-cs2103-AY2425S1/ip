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
                throw new Elseption("Please specify the tag name with /tag.");
            }

            int indexOfTag = input.indexOf("/tag");
            int listIndex = Integer.parseInt(input.substring(6, indexOfTag).strip());
            String tagStr = input.substring(indexOfTag + 5).strip();

            tasklist.untagTask(listIndex, tagStr);
            storage.writeToFile(tasklist);
            return ui.untagTaskMessage(tasklist.getTask(listIndex));

        } catch (Elseption e) {
            return ui.printErrorMessage(e.getMessage());
        }
    }
}
