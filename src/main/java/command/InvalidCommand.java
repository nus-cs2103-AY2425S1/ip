package command;

import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents the invalid command when the
 * chatbot received an unknown command.
 */
public class InvalidCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(String[] parts, TaskList list, Ui ui, Storage storage, Parser parser) {
        return ui.invalidCommand();
    }
}
