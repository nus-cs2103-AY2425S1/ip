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
     * specified keyword in their descriptions. Prints the matching tasks.
     *
     * @param input the user input containing the find command and keyword.
     */
    @Override
    public void run(String input) {
        String sequence = input.split(" ")[1];

        System.out.println("Here are the matching tasks in your list:");

        int count = 1;

        for (int i = 0; i < TaskList.size(); i++) {
            Task task = TaskList.get(i);

            if (task.toString().contains(sequence)) {
                System.out.printf(
                        "%1$s. %2$s%n",
                        count,
                        task
                );
                count++;
            }
        }
    }
}