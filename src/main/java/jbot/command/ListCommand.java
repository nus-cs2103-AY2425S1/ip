package jbot.command;

import jbot.util.TaskList;

/**
 * Represents a command that lists all tasks in the task list.
 * This command retrieves and prints all tasks from the task list, displaying their indices and details.
 * Implements {@link JBotCommand} to adhere to the command interface.
 */
public class ListCommand implements JBotCommand {

    private static final ListCommand instance = new ListCommand();

    private ListCommand() {
    }

    /**
     * Returns the singleton instance of {@link ListCommand}.
     *
     * @return The singleton instance of {@link ListCommand}.
     */
    public static ListCommand getInstance() {
        return instance;
    }

    /**
     * Executes the List command using the provided input string.
     * Returns all tasks in the task list, each with its index and details.
     *
     * @param input The user input string containing the command (not used in this implementation).
     * @return A string containing the list of all tasks in the task list.
     */
    @Override
    public String run(String input) {
        StringBuilder result = new StringBuilder();
        result.append("Here are the tasks in your list:\n");

        for (int i = 0; i < TaskList.size(); i++) {
            result.append(String.format("%1$s. %2$s%n", i + 1, TaskList.get(i)));
        }

        return result.toString();
    }
}