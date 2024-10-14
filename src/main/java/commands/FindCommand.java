package commands;

import applemazer.Storage;
import applemazer.TaskList;
import applemazer.Ui;
import tasks.DuplicateHandler;

/**
 * Class that represents the "find" command.
 */
public class FindCommand extends Command {
    private final String desc;

    /**
     * Constructs a {@code FindCommand} object.
     * <p>
     * @param desc The keyword in the task description to search for.
     */
    public FindCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Executes the "find" command which finds a task by searching for a keyword in the task description.
     *
     * @param tasks The task list to use.
     * @param storage The storage object containing the filepath which the chatbot saves to and loads from.
     * @param ui The Ui object used to generate the string to print.
     * @param duplicateHandler The duplicate handler to use if necessary.
     * @return The string to print.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui, DuplicateHandler duplicateHandler) {
        return ui.getMatchingTaskListString(tasks, desc);
    }

    @Override
    public boolean continueProcessing() {
        return true;
    }
}
