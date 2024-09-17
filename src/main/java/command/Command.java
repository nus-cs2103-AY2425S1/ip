package command;

import exception.CommandFoundButInvalidException;
import main.Storage;
import main.TaskList;
import main.Ui;

/**
 * Represents a command that can be executed to perform a specific action
 */
public interface Command {
    /**
     * Executes the command and perform the corresponding action
     *
     * @param taskList the {@code TaskList} on which command operates on
     * @param ui the {@code Ui} responsible for the displaying of messages
     * @param storage the {@code Storage} instance used save the current existing list of tasks
     * @return the message that indicates the successful execution of the task
     * @throws CommandFoundButInvalidException if the task could not be executed due to invalid inputs
     */
    String execute(TaskList taskList, Ui ui, Storage storage) throws CommandFoundButInvalidException;
}
