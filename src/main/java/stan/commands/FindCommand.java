package stan.commands;

import stan.TaskList;
import stan.Ui;
import stan.Storage;
import stan.tasks.Task;
import stan.exceptions.StanMissingArgumentException;

import java.util.List;

/**
 * Represents a command to find tasks by a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand object.
     *
     * @param words The user's command split into words.
     * @throws StanMissingArgumentException If the keyword is missing.
     */
    public FindCommand(String[] words) throws StanMissingArgumentException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new StanMissingArgumentException("The keyword to find cannot be empty.");
        }
        this.keyword = words[1];
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = tasks.findTasks(keyword);
        ui.showFindResults(matchingTasks);
    }
}
