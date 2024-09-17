package commands;

import exceptions.BrockException;
import storage.task.TaskStorage;
import storage.temp.TempStorage;
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

    private String[] processCommand() {
        String command = super.getCommand();
        return command.split(" ");
    }

    private String checkLength(String[] commandWords) throws BrockException {
        int commandLength = commandWords.length;
        if (commandLength == 1) {
            throw new BrockException("Missing keyword!");
        }
        if (commandLength > 2) {
            throw new BrockException("Please only specify a single keyword!");
        }
        return commandWords[1];
    }

    /**
     * Checks if the find command is valid.
     *
     * @return Keyword if its valid.
     * @throws BrockException If it is missing a keyword or has multiple keywords.
     */
    private String validateFindCommand() throws BrockException {
        String[] commandWords = this.processCommand();
        return checkLength(commandWords);
    }

    private String getResponse(String[] findResult) {
        String resultString = findResult[0];
        int numMatching = Integer.parseInt(findResult[1]);

        String responseHeader = numMatching == 1
                ? "Here is the matching task in your list:\n"
                : "Here are the matching tasks in your list:\n";
        String responseBody = numMatching == 0
                ? "No matching tasks!"
                : resultString;

        return responseHeader + responseBody;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Chatbot iterates through the list of tasks, finding matching tasks that contain the keyword.
     * It then returns these matching tasks, if any.
     * </p>
     *
     * @throws BrockException If the find command is invalid.
     */
    @Override
    public String execute(TaskStorage taskStorage, TempStorage tempStorage, TaskList tasks) throws BrockException {
        String keyword = this.validateFindCommand();
        String[] findResult = tasks.findMatchingTasks(keyword);
        tempStorage.setPreviousCommand("find");

        return this.getResponse(findResult);
    }
}
