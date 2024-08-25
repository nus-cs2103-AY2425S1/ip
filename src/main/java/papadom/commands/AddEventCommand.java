package papadom.commands;

import papadom.Exceptions.IncorrectTaskInputFormatException;
import papadom.tasks.Event;
import papadom.Parser;
import papadom.Storage.Storage;
import papadom.Storage.TaskList;
import papadom.Ui;

public class AddEventCommand extends Command{
    private final String text;
    private static final Parser parser = new Parser();
    public AddEventCommand(String text) {
        this.text = text;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IncorrectTaskInputFormatException {
        try {
            Event eventTask = parser.eventTaskCreator(text.substring(6));
            ui.output(taskList.addToList(eventTask));
        } catch (IndexOutOfBoundsException e) {
            throw new IncorrectTaskInputFormatException();
        }
    }
}
