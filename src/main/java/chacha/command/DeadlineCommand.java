package chacha.command;

import java.time.DateTimeException;

import chacha.ChaCha;
import chacha.Storage;
import chacha.Ui;
import chacha.exception.ChaChaException;
import chacha.exception.EventTimeException;
import chacha.task.Task;
import chacha.task.TaskList;

/**
 * Represents the command to add Deadline Task.
 */
public class DeadlineCommand extends Command {
    private static final String MISSING_DEADLINE_FIELD_MSG =
            "You are missing some info needed (task description, deadline). \n"
                    + "Please type again!";

    public DeadlineCommand(ChaCha chacha) {
        super(chacha);
    }

    /**
     * Returns the string representation of response to adding Deadline task.
     *
     * @param userInput User input.
     * @param storage Storage of ChaCha.
     * @param ui UI of ChaCha.
     * @param tasks List of tasks.
     * @return String representation.
     */
    @Override
    public String execute(String userInput, Storage storage, Ui ui, TaskList tasks) {
        try {
            Task taskAdded = tasks.addDeadline(userInput, ui, storage);
            return ui.printAdd(taskAdded, tasks);

        } catch (ChaChaException e) {
            return MISSING_DEADLINE_FIELD_MSG;
        } catch (EventTimeException e) {
            return e.toString();
        } catch (DateTimeException e) {
            String[] arr = {"Please input the date in the format YYYY-MM-DD. "};
            return ui.printStrings(arr);
        }
    }
}
