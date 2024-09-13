package jbot.command;

import jbot.task.Task;
import jbot.util.TaskList;

/**
 * Represents a command that finds tasks by searching for a keyword
 * in the task descriptions.
 */
public class FindCommand implements JBotCommand {

    /** Singleton instance of the FindCommand. */
    private static final FindCommand instance = new FindCommand();

    /** Private constructor to enforce singleton pattern. */
    private FindCommand() {
    }

    /**
     * Returns the singleton instance of FindCommand.
     *
     * @return the instance of FindCommand.
     */
    public static FindCommand getInstance() {
        return FindCommand.instance;
    }

    /**
     * Executes the find command, searching for tasks that contain the
     * specified keyword in their descriptions. Returns the matching tasks as a string.
     *
     * @param input the user input containing the find command and keyword.
     * @return A string containing the list of matching tasks.
     */
    @Override
    public String run(String input) {
        String sequence = input.split(" ")[1];

        StringBuilder result = new StringBuilder();
        result.append("Here are the matching tasks in your list:\n");

        int count = 1;

        for (int i = 0; i < TaskList.size(); i++) {
            Task task = TaskList.get(i);

            if (task.toString().contains(sequence)) {
                result.append(String.format("%1$s. %2$s%n", count, task));
                count++;
            }
        }

        return result.toString();
    }
}