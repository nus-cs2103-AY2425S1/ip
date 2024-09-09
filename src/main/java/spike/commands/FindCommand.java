package spike.commands;

import spike.exceptions.SpikeException;
import spike.storage.Storage;
import spike.storage.TaskList;
import spike.ui.Ui;

/**
 * Represents a command to find tasks by keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param keyword Keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getCommandType() {
        return "find";
    }

    /**
     * Executes the find command.
     * Finds tasks that contain the keyword and displays them to the user.
     *
     * @param tasks TaskList to find tasks from.
     * @param ui Ui to interact with user.
     * @param storage Storage to save tasks to file.
     * @throws SpikeException If an error occurs while executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpikeException {
        TaskList matchingTasks = tasks.findTask(this.keyword);
        ui.showTaskList(matchingTasks.getAllTasks());
        storage.writeToFile(tasks);
    }

    /**
     * Returns false as the command is not an exit command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
