package papadom.commands;

import papadom.exceptions.IncorrectTaskInputFormatException;
import papadom.tasks.Event;
import papadom.Parser;
import papadom.storage.Storage;
import papadom.storage.TaskList;
import papadom.Ui;

/**
 * Represents a command to add an event task to the task list.
 */
public class AddEventCommand extends Command{
    private final String TEXT;
    private static final Parser PARSER = new Parser();

    /**
     * Constructs an AddEventCommand with the specified text input.
     *
     * @param text The input string that contains the details of the event task.
     */
    public AddEventCommand(String text) {
        this.TEXT = text;
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
            Event eventTask = PARSER.eventTaskCreator(TEXT.substring(6));
            ui.output(taskList.addToList(eventTask));
        } catch (IndexOutOfBoundsException e) {
            throw new IncorrectTaskInputFormatException();
        }
    }
}
