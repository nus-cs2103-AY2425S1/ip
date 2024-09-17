package command;

import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents the mark command which marks the task as done.
 */
public class MarkCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(String[] parts, TaskList list, Ui ui, Storage storage, Parser parser) {
        if (parts.length < 2) {
            return "Please specify the task number to mark \n";
        } else {
            try {
                int index = Integer.parseInt(parts[1]) - 1;
                assert index >= 0 : "Please enter a valid task number\n";
                return list.mark(index);
            } catch (NumberFormatException e) {
                return ui.NumberFormatExceptionMessage();
            }
        }
    }
}
