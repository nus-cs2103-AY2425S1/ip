package muller.command;

import java.util.List;

import muller.storage.Storage;
import muller.task.Task;
import muller.task.TaskList;
import muller.ui.Ui;

/**
 * Command to find tasks containing a specified keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param inputs The command inputs, including the keyword.
     * @throws MullerException If the keyword is missing.
     */
    public FindCommand(String[] inputs) throws MullerException {
        if (CommandUtil.isFindCommandNotValid(inputs)) {
            throw new MullerException("Please provide a keyword to search for!");
        }
        this.keyword = inputs[1].trim();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        CommandUtil.assertionTest(tasks, ui, storage);
        List<Task> matchingTasks = tasks.findTasksByKeyword(keyword);
        return ui.showMatchingTasks(matchingTasks);
    }
}


