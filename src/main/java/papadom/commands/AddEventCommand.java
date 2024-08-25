package papadom.commands;

import papadom.Event;
import papadom.Exceptions.NoDateException;
import papadom.Exceptions.NoTaskException;
import papadom.Parser;
import papadom.Storage.Storage;
import papadom.Storage.TaskList;
import papadom.Todo;
import papadom.Ui;

public class AddEventCommand extends Command{
    private final String text;
    private static final Parser parser = new Parser();
    public AddEventCommand(String text) {
        this.text = text;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws NoDateException, NoTaskException {
        Event eventTask = parser.eventTaskCreator(text.substring(6));
        ui.output(taskList.addToList(eventTask));
    }
}
