package papadom.commands;

import papadom.exceptions.IncorrectTaskInputFormatException;
import papadom.storage.Storage;
import papadom.storage.TaskList;
import papadom.utils.Parser;
import papadom.utils.Ui;

public class FindEventCommand extends Command {
    private static final Parser PARSER = new Parser();
    private final String TEXT;
    public FindEventCommand(String text) {
        this.TEXT = text;
    }
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IncorrectTaskInputFormatException {
        try {
            // Extract out keyword to be searched
            String keyword = PARSER.findKeyword(this.TEXT);
            return ui.output(storage.findTaskBySearching(keyword));
        } catch (IndexOutOfBoundsException e) {
            throw new IncorrectTaskInputFormatException();
        }
    }
}
