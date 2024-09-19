package chacha.command;

import chacha.ChaCha;
import chacha.Storage;
import chacha.Ui;
import chacha.exception.ChaChaException;
import chacha.exception.WrongCommandFormatException;
import chacha.exception.WrongDateFormatException;
import chacha.exception.WrongTimeFormatException;
import chacha.task.Task;
import chacha.task.TaskList;

/**
 * Represents the command to add Event Task.
 */
public class EventCommand extends Command {
    private static final String MISSING_EVENT_FIELD_MSG =
            "You are missing some info needed (task description, date, start time, end time). \n"
                    + "Please type again!";
    public EventCommand(ChaCha chacha) {
        super(chacha);
    }

    /**
     * Returns the string representation of response to adding Event task.
     *
     * @param userInput User input
     * @param storage Storage of ChaCha
     * @param ui UI of ChaCha
     * @param tasks List of tasks
     * @return String representation.
     */
    @Override
    public String execute(String userInput, Storage storage, Ui ui, TaskList tasks) {
        try {
            Task taskAdded = tasks.addEvent(userInput, ui, storage);
            return ui.printAdd(taskAdded, tasks);
        } catch (ChaChaException e) {
            return MISSING_EVENT_FIELD_MSG;
        } catch (WrongCommandFormatException e) {
            return e.toString();
        } catch (WrongDateFormatException e) {
            return e.toString();
        } catch (WrongTimeFormatException e) {
            return e.toString();
        }
    }
}
