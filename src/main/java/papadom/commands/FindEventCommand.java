package papadom.commands;

import papadom.Exceptions.IncorrectTaskInputFormatException;
import papadom.Parser;
import papadom.Storage.Storage;
import papadom.Storage.TaskList;
import papadom.Ui;
import papadom.tasks.Deadline;

public class FindEventCommand extends Command {
    private final String TEXT;
    private static final Parser PARSER = new Parser();

    public FindEventCommand (String text) {
        this.TEXT = text;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IncorrectTaskInputFormatException {
        try {
            String keyword = PARSER.findKeyword(this.TEXT);
            ui.output(storage.findTaskBySearching(keyword));
        } catch (IndexOutOfBoundsException e) {
            throw new IncorrectTaskInputFormatException();
        }
    }
}
