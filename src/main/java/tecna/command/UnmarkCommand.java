package tecna.command;

import tecna.collection.TaskList;
import tecna.exception.WrongFormatException;
import tecna.storage.Storage;
import tecna.ui.Ui;

/**
 * Represents a Command of type UnmarkCommand (unmark a task).
 *
 * @author Adapted from Feng1231.
 */
public class UnmarkCommand extends Command {
    /**
     * Constructs an UnmarkCommand instance.
     *
     * @param message The whole command input in String.
     */
    public UnmarkCommand(String message) {
        super(message);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        super.execute(taskList, storage, ui);
        try {
            int index = parseUnmarkCommand(taskList.getSize());
            taskList.unmark(index);
            return ui.printUnmarkMsg(taskList.getTask(index));
        } catch (WrongFormatException e) {
            return ui.printError(e.getMessage());
        }
    }

    /**
     * Interprets a command of type "unmark".
     *
     * @param taskListSize The size of the current TaskList in the app
     *                     for checking the validity of the input.
     * @return The index of the item needs to be unmarked.
     * @throws WrongFormatException If the command is in wrong format.
     */
    public int parseUnmarkCommand(int taskListSize) throws WrongFormatException {
        String[] input_words = message.split("\\s+");

        if (input_words.length != 2) {
            throw new WrongFormatException("unmark", "Unmark command should be in the format of \"unmark [index of the task from 1 to " + taskListSize +  "]\"");
        }

        try {
            int index =  Integer.parseInt(input_words[1]) - 1;
            if (index < 0 || index > taskListSize - 1) {
                throw new WrongFormatException("unmark", "Unmark command should be in the format of \"unmark [index of the task from 1 to " + taskListSize +  "]\"");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new WrongFormatException("unmark", "Unmark command should be in the format of \"unmark [index of the task from 1 to " + taskListSize +  "]\"");
        }
    }
}
