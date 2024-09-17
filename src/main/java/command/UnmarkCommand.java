package command;

import exception.CommandFoundButInvalidException;
import main.Storage;
import main.TaskList;
import main.Ui;

/**
 * A Command to unmark a specific task in the list
 */
public class UnmarkCommand implements Command {
    private String description;

    /**
     * Constructs a new {@code UnmarkCommand} with the specified description
     *
     * @param description the remaining description of the Unmark command, after the command is removed
     */
    public UnmarkCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the Unmark command by marking a specified task in the {@code TaskList} as not done
     *
     * @param taskList the {@code TaskList} on which command operates on
     * @param ui the {@code Ui} responsible for the displaying of messages
     * @param storage the {@code Storage} instance used save the current existing list of tasks
     * @return the message that indicates the successful execution of the task
     * @throws CommandFoundButInvalidException if the task could not be executed due to invalid inputs
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws CommandFoundButInvalidException {
        taskList.unmark(this.description);
        storage.put(taskList);
        return ui.markedMessage(taskList.getLastUnmarked());
    }
}
