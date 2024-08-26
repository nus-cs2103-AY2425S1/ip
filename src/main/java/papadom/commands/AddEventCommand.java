package papadom.commands;

import papadom.Exceptions.IncorrectTaskInputFormatException;
import papadom.tasks.Event;
import papadom.Parser;
import papadom.Storage.Storage;
import papadom.Storage.TaskList;
import papadom.Ui;
/**
 * Represents a command to add an event task to the task list.
 */
public class AddEventCommand extends Command{
    private final String text;
    private static final Parser parser = new Parser();
    /**
     * Constructs an AddEventCommand with the specified text input.
     *
     * @param text The input string that contains the details of the event task.
     */
    public AddEventCommand(String text) {
        this.text = text;
    }
    /**
     * Executes the command to add an event task to the task list.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface for outputting messages.
     * @param storage The storage system for saving the task list.
     * @throws IncorrectTaskInputFormatException If the input format is invalid.
     */
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
