package command;

import exception.CommandFoundButInvalidException;
import main.Storage;
import main.TaskList;
import main.Ui;

/**
 * A Command to add a new Deadline task to the task list
 */
public class AddDeadlineCommand implements Command {
    private String description;

    /**
     * Constructs a new {@code AddDeadlineCommand} with the specified description
     *
     * @param description the remaining description of the Deadline command, after command is removed
     */
    public AddDeadlineCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the Deadline command by adding it into the {@code TaskList} instance while updating
     * the storage
     *
     * @param taskList the {@code TaskList} on which command operates on
     * @param ui the {@code Ui} responsible for the displaying of messages
     * @param storage the {@code Storage} instance used save the current existing list of tasks
     * @return the message that indicates the successful execution of the task
     * @throws CommandFoundButInvalidException if the task could not be executed due to invalid inputs
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws CommandFoundButInvalidException {
        taskList.addDeadline(this.description);
        storage.put(taskList);
        return ui.addedMessage(taskList.getLastAdded(), taskList.getSize());
    }
}
