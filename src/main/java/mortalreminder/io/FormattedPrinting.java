package mortalreminder.io;

import java.util.ArrayList;

import mortalreminder.backend.tasklistmanager.TaskList;
import mortalreminder.commands.CommandType;
import mortalreminder.errorhandling.MortalReminderException;
import mortalreminder.tasks.Task;

/**
 * This class is just the UI controller to format and ensure consistency of all output messages.
 */
public class FormattedPrinting {

    /**
     * Prints out the formatted output of the chatbot to the user.
     * An indentation is added to each line and the separator lines are added at the top and bottom of each
     * line in the message.
     *
     * @param message output message from chatbot to be printed out to the user
     * @return a version of confirmation message with specific modifications.
     */
    public static String getResponse(String message) {
        return message;
    }

    /**
     * Returns the welcome message to the user upon program startup.
     */
    public static String welcome() {
        return "Hello I'm Mortal Reminder!\n"
                + "What can I do for you?";
    }

    /**
     * Returns all current tasks inside the list when the LIST {@link CommandType} is used.
     *
     * @param taskList the current total list of tasks to be printed out
     * @return a string of the list of all tasks currently tracked.
     * @throws MortalReminderException from an inner method.
     */
    public static String printList(TaskList taskList) throws MortalReminderException {
        if (taskList.getSize() == 0) {
            return getResponse("You have no tasks in your list.");
        } else {
            StringBuilder currentList = new StringBuilder();
            currentList.append("Here are the tasks in your list:\n");
            return listPrintingHelperFunction(taskList, currentList);
        }
    }

    /**
     * Returns all the similar types of tasks queried by the user when the FIND {@link CommandType} is used.
     *
     * @param taskList the list of similar tasks to be printed out.
     * @return a string of all similar tasks to the search term(s) queried by user.
     * @throws MortalReminderException from an inner method.
     */
    public static String printSimilarTasks(TaskList taskList) throws MortalReminderException {
        if (taskList.getSize() == 0) {
            return getResponse("No matching tasks!");
        } else {
            StringBuilder currentList = new StringBuilder();
            currentList.append("Here are the matching tasks in your list:\n");
            return listPrintingHelperFunction(taskList, currentList);
        }
    }

    private static String listPrintingHelperFunction(TaskList taskList, StringBuilder currentList)
            throws MortalReminderException {
        for (int i = 1; i < taskList.getSize() + 1; i++) {
            currentList.append(i).append(".").append(printTask(taskList.getTask(i - 1)));
            if (i < taskList.getSize()) {
                currentList.append("\n");
            }
        }
        return getResponse(currentList.toString());
    }

    /**
     * Returns a formatted string representing the task passed into the method call.
     * Takes the task description, type and icon and formats it properly into a string to be printed.
     *
     * @param task The task to be printed.
     * @return the formatted version of the task in string format.
     */
    public static String printTask(Task task) {
        return "[" + task.getType() + "]"
                + "[" + task.getStatusIcon() + "] "
                + task.getDescription();
    }

    /**
     * Returns a confirmation message when the user adds a new task.
     *
     * @param task     {@link Task} that has been added to the {@link TaskList}
     * @param taskList The current list of all tasks tracked by the backend.
     * @return string of confirmation message.
     */
    public static String addTask(Task task, TaskList taskList) {
        String message = "Got it. I've added this task:\n"
                + printTask(task)
                + "\n"
                + "Now you have "
                + taskList.getSize()
                + " task(s) in the list.";
        return getResponse(message);
    }

    /**
     * Returns a confirmation message when the user deletes a task.
     *
     * @param task     {@link Task} that has been deleted from the {@link TaskList}
     * @param taskList taskList The current list of all tasks tracked by the backend.
     * @return string confirmation message of a successful deletion of task.
     */
    public static String deleteTask(Task task, TaskList taskList) {
        String message = "Got it. I've deleted this task:\n"
                + printTask(task)
                + "\n"
                + "Now you have "
                + taskList.getSize()
                + " task(s) in the list.";
        return getResponse(message);
    }

    public static String clearList() {
        return getResponse("List has been cleared.");
    }

    public static String printMarked(Task task) {
        return getResponse("Nice!  I've marked this task as done:\n" + printTask(task));
    }

    public static String printUnmarked(Task task) {
        return getResponse("OK, I've marked this task as not done yet:\n" + printTask(task));
    }

    /**
     * Returns a list of all upcoming tasks from today.
     * These tasks must be {@link mortalreminder.tasks.TimedTask} and not have been marked as done.
     *
     * @param tasks The list of all tasks that are upcoming from today and is tracked by the backend.
     * @return a string formatted version of all upcoming tasks from now that have not been marked as done.
     * @throws MortalReminderException if the {@link TaskList} is empty.
     */
    public static String printUpcomingDeadlinesEvents(ArrayList<Task> tasks) throws MortalReminderException {
        if (tasks.isEmpty()) {
            throw new MortalReminderException("There are no upcoming tasks!");
        } else {
            StringBuilder output = new StringBuilder("The following tasks are due soon:\n");
            for (Task task : tasks) {
                output.append(printTask(task)).append("\n");
            }
            return getResponse(output.toString());
        }
    }

    /**
     * Prints a feedback message when a new alternative has been added.
     */
    public static String printNewAlternativeAdded(String commandAlternative, CommandType commandType) {
        return getResponse(commandAlternative.toLowerCase()
                + " has been added to the command alternatives for "
                + commandType);
    }

    public static String alternativesCleared() {
        return getResponse("The command alternatives have been cleared.");
    }
}
