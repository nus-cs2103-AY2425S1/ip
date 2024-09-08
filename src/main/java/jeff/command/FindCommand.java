package jeff.command;

import java.util.List;

import jeff.exception.JeffException;
import jeff.parser.Parser;
import jeff.storage.Storage;
import jeff.task.Task;
import jeff.task.TaskList;

/**
 * Represents a "Find task" command.
 */
public class FindCommand extends Command {
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
        
        if (this.getInput().matches("find .+")) {
            // Split the input to get the filter name
            String[] taskParts = this.getInput().split(" ", 2);
            assert taskParts.length == 2 : "Task parts should be of length 2";

            String filterName = taskParts.length > 1 ? taskParts[1] : "";
            assert !filterName.isEmpty() : "Filter name should not be empty";

            // Check if the filter name is empty
            if (filterName.isEmpty()) {
                throw new JeffException("The format is wrong! It should be \"find xx\"!");
            }

            // Filter the task list by the filter name
            List<Task> filteredTasks = tasks.filterByName(filterName);
            assert filteredTasks != null : "Filtered tasks should not be null";

            // Check if the list is empty
            if (filteredTasks.isEmpty()) {
                throw new JeffException("Sorry, no task contains the phrase " + filterName + ".");
            }

            // Convert the list of tasks to a string
            String taskListString = Parser.listToString(filteredTasks);
            assert !taskListString.isEmpty() : "Task list string should not be empty";

            // Return the filtered list string representation
            return Parser.prettyText("Here are the matching tasks in your list:\n" + taskListString);
        } else {
            throw new JeffException("The format is wrong! It should be \"find xx\"!");
        }

    }
}
