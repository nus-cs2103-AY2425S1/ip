package papadom.commands;

import papadom.tasks.Deadline;
import papadom.Exceptions.IncorrectTaskInputFormatException;
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
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IncorrectTaskInputFormatException {
        try {
            Deadline deadlineTask = parser.deadlineTaskCreator(text.substring(9));
            ui.output(taskList.addToList(deadlineTask));
        } catch (IndexOutOfBoundsException e) {
            throw new IncorrectTaskInputFormatException();
        }
    }
}
