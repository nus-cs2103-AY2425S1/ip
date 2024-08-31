package mortalreminder.io;

import java.util.ArrayList;
import java.util.Arrays;

import mortalreminder.backend.TaskList;
import mortalreminder.commands.CommandTypes;
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
     */
    public static String getResponse(String message) {
        String indentation = "   ";
        String separatorLine = indentation + "______________________________________________________";
        StringBuilder outputMessage = new StringBuilder(separatorLine);
        String[] messageLines = message.split("\n");
        for (String line : messageLines) {
            outputMessage.append("\n")
                    .append(indentation)
                    .append(line);
        }
        outputMessage.append("\n")
                .append(separatorLine);
        return outputMessage.toString();
    }

    /**
     * Prints the welcome message to the user upon program startup.
     */
    public static String welcome() {
        String welcomeMessage = "Hello I'm Mortal Reminder!\n"
                + "What can I do for you?";
        return getResponse(welcomeMessage);
    }

    /**
     * Prints the goodbye message to the user when the user types bye into the command-line.
     */
    public static String goodbye() {
        return getResponse("Bye. Hope to see you again soon!");
    }

    /**
     * Prints all current tasks inside the list when the LIST {@link CommandTypes} is used.
     *
     * @param taskList the current total list of tasks to be printed out
     */
    public static String printList(TaskList taskList) {
        if (taskList.getSize() == 0) {
            return emptyList();
        } else {
            StringBuilder currentList = new StringBuilder();
            currentList.append("Here are the tasks in your list:\n");
            return addListItems(taskList, currentList);
        }
    }

    /**
     * Prints all the similar types of tasks queried by the user when the FIND {@link CommandTypes} is used.
     *
     * @param taskList the list of similar tasks to be printed out.
     */
    public static String printSimilarTasks(TaskList taskList) {
        if (taskList.getSize() == 0) {
            return getResponse("No matching tasks!");
        } else {
            StringBuilder currentList = new StringBuilder();
            currentList.append("Here are the matching tasks in your list:\n");
            return addListItems(taskList, currentList);
        }
    }

    private static String addListItems(TaskList taskList, StringBuilder currentList) {
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
     * Prints a confirmation message when the user adds a new task.
     *
     * @param task {@link Task} that has been added to the {@link TaskList}
     * @param taskList The current list of all tasks tracked by the backend.
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
     * Prints a confirmation message when the user deletes a task.
     *
     * @param task {@link Task} that has been deleted from the {@link TaskList}
     * @param taskList taskList The current list of all tasks tracked by the backend.
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

    public static String descriptionEmptyError() {
        return getResponse("You have an empty description. Please try again.");
    }

    public static String markError() {
        return getResponse("This task has already been marked as done.");
    }

    public static String unmarkError() {
        return getResponse("This task has already been marked as not done.");
    }

    /**
     * Prints the output message when an unknown command is passed in by the user.
     */
    public static String unknownCommand() {
        String outputMessage = "I do not recognise this command, please check again!\n"
                + "Available commands are:\n"
                + Arrays.toString(CommandTypes.class.getEnumConstants()).toLowerCase();
        return getResponse(outputMessage);
    }

    public static String unknownNumber() {
        return getResponse("Please enter a valid number after the command!");
    }

    public static String emptyList() {
        return getResponse("You have no tasks in your list.");
    }

    /**
     * @param taskList The current list of all tasks tracked by the backend.
     */
    public static String outOfListBounds(TaskList taskList) {
        return getResponse("Invalid task number!\n"
                + "Please input a number between 1 and "
                + taskList.getSize());
    }

    public static String invalidNumberOfDetails() {
        return getResponse("Please give the correct amount of information for the command!");
    }

    public static String taskUnableToBeStoredInFile() {
        return getResponse("There was an error in adding the task to the storage file!");
    }

    /**
     * Prints an error message when the file cannot be loaded properly due to a corrupted text file.
     */
    public static String fileCorrupted() {
        return getResponse("The storage file has been corrupted.\n"
                + "Use the clear_tasks command to get rid of this!");
    }

    public static String invalidDate() {
        return getResponse("Please enter a valid date in dd-MM-yyy HHmm (24hr format)!.");
    }

    /**
     * Prints a list of all upcoming tasks from today.
     * These tasks must be {@link mortalreminder.tasks.TimedTask} and not have been marked as done.
     *
     * @param tasks The list of all tasks that are upcoming from today and is tracked by the backend.
     */
    public static String upcomingDeadlinesEvents(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return getResponse("There are no upcoming tasks.");
        } else {
            StringBuilder output = new StringBuilder("The following tasks are due soon:\n");
            for (Task task : tasks) {
                output.append(printTask(task)).append("\n");
            }
            return getResponse(output.toString());
        }
    }

}
