package papadom.commands;

import papadom.exceptions.IncorrectTaskInputFormatException;
import papadom.storage.Storage;
import papadom.storage.TaskList;
import papadom.tasks.Deadline;
import papadom.utils.Parser;
import papadom.utils.Ui;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class AddDeadlineCommand extends Command {
    private final String TEXT;
    private final static Parser PARSER = new Parser();

    /**
     * Constructs an AddDeadlineCommand with the specified text input.
     *
     * @param text The input string that contains the details of the deadline task.
     */
    public AddDeadlineCommand(String text) {
        this.TEXT = text;
    }

    /**
     * Executes the command to add a deadline task to the task list.
     *
     * @param taskList The list of tasks.
     * @param ui       The user interface for outputting messages.
     * @param storage  The storage system for saving the task list.
     * @throws IncorrectTaskInputFormatException If the input format is invalid.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IncorrectTaskInputFormatException {
        try {
            Deadline deadlineTask = PARSER.createDeadlineTask(TEXT.substring(9));
            return ui.output(taskList.addToList(deadlineTask));
        } catch (IndexOutOfBoundsException e) {
            throw new IncorrectTaskInputFormatException();
        }
    }
}
