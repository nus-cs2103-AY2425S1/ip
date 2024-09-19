package elster.commands;

import elster.Elseption;
import elster.Storage;
import elster.TaskList;
import elster.Ui;

/**
 * MarkCommand class to represent a command that is executed when the user marks a task as done.
 */
public class MarkCommand extends Command {
    private MarkCommand(Ui ui, TaskList tasklist, Storage storage, String input) {
        super(ui, tasklist, storage, input);
    }

    public static MarkCommand of(Ui ui, TaskList tasklist, Storage storage, String input) {
        return new MarkCommand(ui, tasklist, storage, input);
    }
    @Override
    public String execute() {
        try {
            int index = Integer.parseInt(input.substring(5).strip());

            if (tasklist.markTaskAsDone(index)) {
                storage.writeToFile(tasklist);
                return ui.taskDoneMessage(tasklist.getTask(index));
            }
            assert false;
            return null;
        } catch (Elseption e) {
            return ui.printErrorMessage(e.getMessage());
        }
    }
}
