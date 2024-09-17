package command;

import exception.CommandFoundButInvalidException;
import main.Storage;
import main.TaskList;
import main.Ui;

/**
 * A Command to mark a specific task in the list
 */
public class MarkCommand implements Command {
    private String description;

    /**
     * Constructs a new {@code MarkCommand} with the specified description
     *
     * @param description the remaining description of the mark command, after the command is removed
     */
    public MarkCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the Mark command by marking a specified task in the {@code TaskList} as done
     *
     * @param taskList the {@code TaskList} on which command operates on
     * @param ui the {@code Ui} responsible for the displaying of messages
     * @param storage the {@code Storage} instance used save the current existing list of tasks
     * @return the message that indicates the successful execution of the task
     * @throws CommandFoundButInvalidException if the task could not be executed due to invalid inputs
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws CommandFoundButInvalidException {
        taskList.mark(this.description);
        storage.put(taskList);
        return ui.markedMessage(taskList.getLastMarked());
    }
}
