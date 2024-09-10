package commands;

import exceptions.BrockException;
import storage.Storage;
import task.TaskList;

/**
 * Represents a find command entered by the user.
 */
public class FindCommand extends Command {

    /**
     * Stores the command string associated with find command.
     *
     * @param command Command string.
     */
    public FindCommand(String command) {
        super(command);
    }

    /**
     * Checks if the find command is valid.
     *
     * @return Keyword if its valid.
     * @throws BrockException If it is missing a keyword or has multiple keywords.
     */
    private String validateFindCommand() throws BrockException {
        String command = super.getCommand();
        String[] parts = command.split(" ");
        if (parts.length == 1) {
            throw new BrockException("Missing keyword!");
        }
        if (parts.length > 2) {
            throw new BrockException("Please only specify a single keyword!");
        }
        return parts[1];
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Chatbot iterates through the list of tasks, finding matching tasks that contain the keyword.
     * It then displays these matching tasks, if any.
     * </p>
     *
     * @throws BrockException If the find command is invalid.
     */
    @Override
    public String execute(Storage storage, TaskList tasks) throws BrockException {
        String keyword = validateFindCommand();

        String[] result = tasks.findMatchingTasks(keyword);
        String resultString = result[0];
        int numMatching = Integer.parseInt(result[1]);

        String responseHeader = numMatching == 1
                ? "Here is the matching task in your list:\n"
                : "Here are the matching tasks in your list:\n";
        String responseBody = numMatching == 0
                ? "No matching tasks!"
                : resultString;
        return responseHeader + responseBody;
    }
}
