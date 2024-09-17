package command;

import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents the find command which filters the task
 * list based on the word.
 */
public class FindCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(String[] parts, TaskList list, Ui ui, Storage storage, Parser parser) {
        try {
            return list.find(parts[1]).printList();
        } catch (StringIndexOutOfBoundsException e) {
            return "No such items in the list!\n";
        }
    }
}
