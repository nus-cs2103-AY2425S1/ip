package command;

import exception.CommandFoundButInvalidException;
import main.Storage;
import main.TaskList;
import main.Ui;

/**
 * A Command that sorts the tasks in the task list
 */
public class SortCommand implements Command {
    private String description;

    /**
     * Constructs a new {@code SortCommand} with the specified description
     * @param description
     */
    public SortCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the Sort command as specified by the compare method in the TaskComparator class
     *
     * @param taskList the {@code TaskList} on which command operates on
     * @param ui the {@code Ui} responsible for the displaying of messages
     * @param storage the {@code Storage} instance used save the current existing list of tasks
     * @return the message that indicates the successful execution of the task
     * @throws CommandFoundButInvalidException if the task could not be executed due to invalid inputs
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws CommandFoundButInvalidException {
        return ui.sortMessage() + "\n"
                + new TaskList(taskList.sort(this.description)).list("");
    }
}
