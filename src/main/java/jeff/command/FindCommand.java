package jeff.command;

import jeff.exception.JeffException;
import jeff.parser.Parser;
import jeff.storage.Storage;
import jeff.task.TaskList;

/**
 * Represents a "Find task" command.
 */
public class FindCommand extends Command {
    private static final String WRONG_FORMAT_ERROR = "The format is wrong! It should be \"find(or f) xx\"!";
    /**
     * Constructor for FindCommand Class.
     * Stores the user's input.
     *
     * @param input User's input.
     */
    public FindCommand(String input) {
        super(input);
    }

    /**
     * Checks if the format of the user input is wrong or not.
     *
     * @return true if the user input is in the wrong format and false otherwise.
     */
    private boolean isFormatWrong() {
        return !this.getInput().matches("find .+") && !this.getInput().matches("f .+");
    }

    /**
     * Returns the search text to filter by.
     *
     * @return Search text.
     * @throws JeffException if there is no search text.
     */
    private String getSearchText() throws JeffException {
        if (this.isFormatWrong()) {
            throw new JeffException(WRONG_FORMAT_ERROR);
        }

        String[] taskParts = this.getInput().split(" ", 2);
        String searchText = taskParts.length > 1 ? taskParts[1] : "";

        if (searchText.isEmpty()) {
            throw new JeffException(WRONG_FORMAT_ERROR);
        }

        return searchText;
    }

    /**
     * Returns a TaskList filtered by the given search text.
     *
     * @param tasks Task list.
     * @param searchText String to filter by.
     * @return Filtered task list.
     * @throws JeffException if there is no task with the given search text in its name.
     */
    private TaskList filterBySearchText(TaskList tasks, String searchText) throws JeffException {
        TaskList filteredTasks = tasks.filterByName(searchText);
        assert filteredTasks != null : "Filtered tasks should not be null";

        if (filteredTasks.isEmpty()) {
            throw new JeffException("Sorry, no task contains the phrase " + searchText + ".");
        }

        return filteredTasks;
    }

    /**
     * Returns the string representation of the response by the chatbot Jeff when the user searches for tasks by
     * search text by filtering the search text in the task list.
     *
     * @param tasks Task list.
     * @param storage Place to get and write the task list to the tasks text file.
     * @return String representation of the response.
     * @throws JeffException if the user's input is in the wrong format or if no task match the user's input.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws JeffException {
        assert tasks != null : "Task list should not be null";
        assert storage != null : "Storage should not be null";

        String searchText = this.getSearchText();
        TaskList filteredTasks = this.filterBySearchText(tasks, searchText);
        String taskListString = filteredTasks.toString();

        return Parser.addSpaceInFrontOfEachLine(
                "Here are the matching tasks in your list:\n" + taskListString
        );
    }
}
