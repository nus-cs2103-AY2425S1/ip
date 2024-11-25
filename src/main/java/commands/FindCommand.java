package commands;

import exceptions.EchoException;
import tasks.TaskList;

/**
 * Represents a command to search for tasks in the task list based on keywords.
 * This class provides functionality to find and list tasks whose descriptions contain the specified keywords.
 */
public class FindCommand {

    /**
     * Prints tasks in the task list with provided keywords.
     *
     * @param commandArray command user input.
     * @param allTasks task list.
     * @return A string containing the list of tasks that match the search keywords.
     */
    public static String run(String[] commandArray, TaskList allTasks) throws EchoException {
        if (commandArray.length < 2) {
            throw new EchoException("Oops! Your find command is invalid.");
        }

        String keywords = commandArray[1];
        return allTasks.find(keywords);
    }

    /** Sends help information of command 'find' to user */
    public static String getHelpMessage() {
        return "Find a task by searching for a keyword in the task description.\n\n"
                + "Format:\n"
                + "\t find<whitespace>[keywords]\n"
                + "- [keywords] is the keyword in that description of target tasks.\n\n"
                + "Remark: Please enter only one whitespace.";
    }
}
