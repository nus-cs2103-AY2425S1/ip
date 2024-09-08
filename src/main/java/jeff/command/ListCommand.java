package jeff.command;

import jeff.exception.JeffException;
import jeff.parser.Parser;
import jeff.storage.Storage;
import jeff.task.TaskList;

/**
 * Represents a "Show task list" command.
 */
public class ListCommand extends Command {
    /**
     * Constructor for ListCommand Class.
     * Stores the user's input.
     *
     * @param input User's input.
     */
    public ListCommand(String input) {
        super(input);
    }

    /**
     * Returns the string representation of the task list by the chatbot Jeff when the user wants to see the task list.
     *
     * @param tasks Task list.
     * @param storage Place to get and write the task list to the tasks text file.
     * @return String representation of the task list.
     * @throws JeffException if the task list is empty.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws JeffException {
        assert tasks != null : "Task list should not be null";

        // Check if the list is empty
        if (tasks.isEmpty()) {
            throw new JeffException("List is empty!");
        }

        // Initialise a StringBuilder
        StringBuilder listString = new StringBuilder();

        // Loop through the inputList and add it to the StringBuilder
        for (int i = 0; i < tasks.size(); i++) {
            listString.append(Integer.toString(i + 1)).append(".").append(tasks.get(i).toString());

            // Only add a new line when it is not the last task in the list
            if (i != tasks.size() - 1) {
                listString.append("\n");
            }

        }

        // Return the task list string representation
        return Parser.prettyText(listString.toString());
    }
}
