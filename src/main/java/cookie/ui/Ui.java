package cookie.ui;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import cookie.task.Task;

/**
 * Handles the user interface operations for displaying messages and task information.
 */
public class Ui {

    /**
     * Returns a greeting message from the application.
     *
     * @return the greeting message
     */
    public String printGreet() {
        return "Hello! I'm Cookie\n"
                + "How can I help you?\n"
                + "Here are some commands you can use:\n"
                + "todo, deadline, event, mark, unmark, delete, list, find, alias and set";
    }

    /**
     * Returns the exit message when the application is closed.
     *
     * @return the exit message
     */
    public String printQuit() {
        return "Bye. See you soon!";
    }

    /**
     * Returns the number of task.
     */
    public String printNoTasksInList(ArrayList<Task> taskArrayList) {
        if (taskArrayList.size() == 1) {
            return "Now you have " + taskArrayList.size() + " task in the list.\n";
        } else {
            return "Now you have " + taskArrayList.size() + " tasks in the list.\n";
        }
    }

    /**
     * Returns a message about the latest task added to the list.
     *
     * @param task the latest task added
     * @return the message about the added task
     */
    public String printLatestTask(Task task) {
        return "Got it. Cookie has added this task:\n" + task.toString() + "\n";
    }

    /**
     * Returns a message indicating that a task has been deleted from the list.
     *
     * @param task the task that was deleted
     * @return the message about the deleted task
     */
    public String printDeleteTask(Task task) {
        return "Cookie has removed the following task from your list:\n" + task.toString();
    }

    /**
     * Returns a message indicating that a task has been marked as done.
     *
     * @param task the task that was marked as done
     * @return the message about the marked task
     */
    public String printMarkTask(Task task) {
        return "Cookie has marked this as done! Good job!\n" + task.toString();
    }

    /**
     * Returns a message indicating that a task has been marked as not done.
     *
     * @param task the task that was unmarked
     * @return the message about the unmarked task
     */
    public String printUnmarkTask(Task task) {
        return "Cookie has unmarked this task!\n" + task.toString();
    }

    /**
     * Returns a string representation of all tasks in the list.
     *
     * @param taskArrayList the list of tasks to be displayed
     * @return the string representation of all tasks
     */
    public String printTasks(ArrayList<Task> taskArrayList) {
        if (taskArrayList.isEmpty()) {
            return "You currently have no tasks! Good job!";
        }

        int count = 1;
        StringBuilder list = new StringBuilder();
        for (Task task: taskArrayList) {
            list.append(count++).append(": ").append(task.toString()).append("\n");
        }
        return list.toString();
    }

    /**
     * Generates a success message indicating that a command has been successfully set with a new alias.
     *
     * @param command the original command that has been aliased
     * @param alias the new alias set for the command
     * @return a formatted success message stating that the alias has been set
     */
    public String setAsAliasSuccess(String command, String alias) {
        return String.format("You have successfully set %s as %s", command, alias);
    }

    /**
     * Iterates over the provided Hashtable of command aliases and returns a formatted string
     * with each alias and its corresponding command.
     *
     * @param commandAliasesMap the Hashtable containing command aliases as keys and their
     *                          corresponding commands as values.
     * @return a formatted String containing all alias-command pairs from the hashtable.
     */
    public String printAllAlias(Hashtable<String, String> commandAliasesMap) {
        if (commandAliasesMap.isEmpty()) {
            return "No aliases exists! You can use \"set [command] [alias]\" to create an alias for your command!";
        }

        StringBuilder result = new StringBuilder("Here are your aliases and their corresponding command:\n");

        for (Map.Entry<String, String> entry : commandAliasesMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            result.append("Alias: ").append(key).append(", Command: ").append(value).append("\n");
        }

        return result.toString();
    }
}
