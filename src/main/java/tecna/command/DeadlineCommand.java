package tecna.command;

import java.time.format.DateTimeParseException;

import tecna.collection.TaskList;
import tecna.exception.WrongFormatException;
import tecna.exception.TaskDuplicateException;
import tecna.storage.Storage;
import tecna.task.Deadline;
import tecna.ui.Ui;
import tecna.util.DateTimeUtil;

import static tecna.util.DateTimeUtil.DATE_TIME_PATTERN;

/**
 * Represents the Command of type DeadlineCommand (add a deadline task).
 *
 * @author Adapted from Feng1231.
 */
public class DeadlineCommand extends Command {
    /**
     * Constructs a DeadlineCommand instance.
     *
     * @param message The whole command input in String.
     */
    public DeadlineCommand(String message) {
        super(message);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        super.execute(taskList, storage, ui);
        Deadline deadline;

        try {
            deadline = parseDeadlineCommand();
        } catch (WrongFormatException e) {
            return ui.printError(e.getMessage());
        }

        assert deadline != null;
        try {
            taskList.addItem(deadline);
        } catch (TaskDuplicateException e) {
            return ui.printTaskDuplicateError(deadline);
        }
        return ui.printAddItemMsg(taskList, deadline);
    }

    /**
     * Interprets the input.
     *
     * @return A Deadline object if the input is valid.
     * @throws WrongFormatException If the Deadline command is in wrong format.
     */
    public Deadline parseDeadlineCommand() throws WrongFormatException {
        Deadline deadline = null;

        assert message.contains("deadline");

        String[] description = message.split("deadline\\s+|/by\\s+", -1);

        if (description[1].isBlank()) {
            throw new WrongFormatException("deadline", "Deadline task's [task name] must not be empty!");
        }

        try {
            deadline = new Deadline(description[1].trim(), DateTimeUtil.parseDateTime(description[2].trim()));
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new WrongFormatException("deadline", "Deadline task should be in the format \"deadline [task name] /by [" + DATE_TIME_PATTERN + "]\"");

        }
        return deadline;
    }
}
