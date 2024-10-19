package command;

import java.util.stream.Stream;

import exception.CommandFoundButInvalidException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

/**
 * A Command to find tasks in the task list that contains the user's input
 */
public class FindCommand implements Command {
    private String description;

    /**
     * Constructs a new {@code FindCommand} with the specified description
     *
     * @param description the remaining description of the Find command, after the command is removed
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the Find command to find all tasks that contains the user specified input
     *
     * @param taskList the {@code TaskList} on which command operates on
     * @param ui the {@code Ui} responsible for the displaying of messages
     * @param storage the {@code Storage} instance used save the current existing list of tasks
     * @return the message that indicates the successful execution of the task
     * @throws CommandFoundButInvalidException if the task could not be executed due to invalid inputs
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws CommandFoundButInvalidException {
        Stream<Task> result = taskList.find(this.description);
        return ui.findMessage() + "\n"
                + new TaskList(result.toList()).list("");
    }
}
