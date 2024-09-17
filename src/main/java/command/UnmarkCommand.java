package command;

import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents the unmark command which makes the task not done.
 */
public class UnmarkCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(String[] parts, TaskList list, Ui ui, Storage storage, Parser parser) {
        if (parts.length < 2) {
            return "Please specify the task number to unmark \n";
        } else {
            try {
                int index = Integer.parseInt(parts[1]) - 1;
                assert index >= 0 : "Please enter a valid task number\n";
                return list.unmark(index);
            } catch (NumberFormatException e) {
                return ui.NumberFormatExceptionMessage();
            }
        }
    }
}
