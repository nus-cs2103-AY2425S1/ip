package jeff.command;

import jeff.exception.JeffException;
import jeff.parser.Parser;
import jeff.storage.Storage;
import jeff.task.TaskList;

/**
 * Represents an "Exit the program" command.
 */
public class ExitCommand extends Command {
    /**
     * Constructor for ExitCommand Class.
     * Stores the user's input.
     *
     * @param input User's input.
     */
    public ExitCommand(String input) {
        super(input);
    }

    /**
     * Returns the string representation of the response by the chatbot Jeff when the user is exiting the program.
     *
     * @param tasks Task list.
     * @param storage Place to get and write the task list to the tasks text file.
     * @return String representation of the response.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws JeffException {
        storage.updateTaskListInDatabase(tasks);
        return Parser.addSpaceInFrontOfEachLine("Bye. Hope to see you again soon!");
    }

    /**
     * Returns true.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
