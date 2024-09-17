package command;

import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents the list command which shows the list of tasks.
 */
public class ListCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(String[] parts, TaskList list, Ui ui, Storage storage, Parser parser) {
        if (parts.length > 1) {
            return ui.invalidListCommandMessage();
        }
        return list.printList() + "\n";
    }
}
