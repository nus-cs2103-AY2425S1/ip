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
        if (tasks.isEmpty()) {
            throw new JeffException("List is empty!");
        }

        String taskListString = tasks.toString();

        return Parser.addSpaceInFrontOfEachLine(taskListString);
    }
}
