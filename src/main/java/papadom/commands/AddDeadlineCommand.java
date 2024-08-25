package papadom.commands;

import papadom.Deadline;
import papadom.Event;
import papadom.Exceptions.IncorrectDeadlineDateFormatException;
import papadom.Exceptions.NoDateException;
import papadom.Exceptions.NoTaskException;
import papadom.Parser;
import papadom.Storage.Storage;
import papadom.Storage.TaskList;
import papadom.Ui;

public class AddDeadlineCommand extends Command {
    private final String text;
    private static final Parser parser = new Parser();
    public AddDeadlineCommand(String text) {
        this.text = text;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws NoDateException, NoTaskException, IncorrectDeadlineDateFormatException {
        Deadline deadlineTask = parser.deadlineTaskCreator(text.substring(9));
        ui.output(taskList.addToList(deadlineTask));
    }
}
