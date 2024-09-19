package elster.commands;

import elster.Elseption;
import elster.Storage;
import elster.TaskList;
import elster.Ui;

/**
 * UnmarkCommand class to represent a command that is executed when the user marks a task as undone.
 */
public class UnmarkCommand extends Command {
    private UnmarkCommand(Ui ui, TaskList tasklist, Storage storage, String input) {
        super(ui, tasklist, storage, input);
    }

    public static UnmarkCommand of(Ui ui, TaskList tasklist, Storage storage, String input) {
        return new UnmarkCommand(ui, tasklist, storage, input);
    }
    @Override
    public String execute() {
        try {
            int index = Integer.parseInt(input.substring(7).strip());

            if (tasklist.unmarkTaskAsUndone(index)) {
                storage.writeToFile(tasklist);
                return ui.taskUndoneMessage(tasklist.getTask(index));
            }
            assert false;
            return null;
        } catch (Elseption e) {
            return ui.printErrorMessage(e.getMessage());
        }
    }
}
