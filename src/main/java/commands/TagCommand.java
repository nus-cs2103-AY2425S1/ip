package commands;

import exceptions.EchoException;
import tasks.TaskList;

/**
 * Represents a command to tag a task in the task list.
 * This class provides functionality to add a tag or label to a specific task
 * based on its index in the task list.
 */
public class TagCommand {

    /**
     * Tag the complete status of a target task from task list using "tag [index] /t #info" command
     *
     * @param commandArray command user input.
     * @param allTasks task list.
     * @return A string confirming that the task has been tag.
     * @throws IllegalArgumentException If the index is out of bounds.
     */
    public static String run(String[] commandArray, TaskList allTasks) throws EchoException {
        if (commandArray.length < 2) {
            throw new EchoException("Oops! Your tag command is invalid.");
        }

        if (commandArray[1].split(" ").length != 2) {
            throw new EchoException("Oops! Your tag command is invalid.");
        }

        try {
            int index = Integer.parseInt(commandArray[1].split(" ", 2)[0]) - 1;
            String tag = commandArray[1].split(" ", 2)[1];
            return allTasks.tagTask(index, tag);
        } catch (AssertionError e) {
            throw new EchoException(e.getMessage());
        }
    }

    /** Sends help information of command 'tag' to user */
    public static String getHelpMessage() {
        return "Tag a task in the task list.\n\n"
                + "Format:\n"
                + "\t tag<whitespace>[index]<whitespace>[tag] \n"
                + "- [index] is the index of task you want to mark.\n"
                + "- [tag] is the information you want to tag to a task.\n\n"
                + "Remark: Please enter only one whitespace and correct index";
    }
}
