package command;

import exception.CommandFoundButInvalidException;
import main.Storage;
import main.TaskList;
import main.Ui;

/**
 * A Command to list all the tasks in the task list
 */
public class ListCommand implements Command {
    private String description;

    /**
     * Constructs a new {@code ListCommand} with the specified description
     *
     * @param description the remaining description of the List command, after the command is removed
     */
    public ListCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the List command
     *
     * @param taskList the {@code TaskList} on which command operates on
     * @param ui the {@code Ui} responsible for the displaying of messages
     * @param storage the {@code Storage} instance used save the current existing list of tasks
     * @return the message that indicates the successful execution of the task
     * @throws CommandFoundButInvalidException if the task could not be executed due to invalid inputs
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws CommandFoundButInvalidException {
        return taskList.list(this.description);
    }
}
