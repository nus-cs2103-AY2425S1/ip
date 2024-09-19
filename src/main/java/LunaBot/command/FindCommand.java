package lunabot.command;

import java.util.ArrayList;

import lunabot.exception.LunaBotException;
import lunabot.storage.Storage;
import lunabot.task.Task;
import lunabot.task.TaskList;
import lunabot.ui.Ui;

/**
 * Command to find tasks with a matching keyword from the taskList.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand from user input.
     *
     * @param input Full user input containing the keyword.
     * @throws LunaBotException Handles empty keyword or wrong input format.
     */
    public FindCommand(String input) throws LunaBotException {
        try {
            this.keyword = input.substring(5).trim();
            if (keyword.isEmpty()) {
                throw new LunaBotException("Keyword field cannot be empty");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new LunaBotException("Invalid find command format");
        }
    }

    /**
     * @param taskList Current list of tasks to be operated on
     * @param ui User interface that handles user input and interactions
     * @param storage Storage system to save or load tasks to/from a file.
     * @throws LunaBotException If an error occurs while executing the command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws LunaBotException {
        assert taskList != null : "Task list cannot be null";
        assert ui != null : "UI cannot be null";
        assert storage != null : "Storage cannot be null";

        ArrayList<Task> matchingTasks = taskList.findTasks(keyword);
        return ui.printMatchingTasks(matchingTasks);
    }
}
