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
    public static void formatPrint(String message) {
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
        System.out.println(outputMessage);
    }

    /**
     * Prints the welcome message to the user upon program startup.
     */
    public static void welcome() {
        String welcomeMessage = "Hello I'm Mortal Reminder!\n"
                + "What can I do for you?";
        formatPrint(welcomeMessage);

    }

    /**
     * Prints the goodbye message to the user when the user types bye into the command-line.
     */
    public static void goodbye() {
        formatPrint("Bye. Hope to see you again soon!");
    }

    /**
     * Prints all current tasks inside the list when the LIST {@link CommandTypes} is used.
     *
     * @param taskList the current total list of tasks to be printed out
     */
    public static void printList(TaskList taskList) {
        if (taskList.getSize() == 0) {
            emptyList();
        } else {
            StringBuilder currentList = new StringBuilder();
            currentList.append("Here are the tasks in your list:\n");
            addListItems(taskList, currentList);
        }
    }

    /**
     * Prints all the similar types of tasks queried by the user when the FIND {@link CommandTypes} is used.
     *
     * @param taskList the list of similar tasks to be printed out.
     */
    public static void printSimilarTasks(TaskList taskList) {
        if (taskList.getSize() == 0) {
            formatPrint("No matching tasks!");
        } else {
            StringBuilder currentList = new StringBuilder();
            currentList.append("Here are the matching tasks in your list:\n");
            addListItems(taskList, currentList);
        }
    }

    private static void addListItems(TaskList taskList, StringBuilder currentList) {
        for (int i = 1; i < taskList.getSize() + 1; i++) {
            currentList.append(i).append(".").append(printTask(taskList.getTask(i - 1)));
            if (i < taskList.getSize()) {
                currentList.append("\n");
            }
        }
        formatPrint(currentList.toString());
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
    public static void addTask(Task task, TaskList taskList) {
        String message = "Got it. I've added this task:\n"
                + printTask(task)
                + "\n"
                + "Now you have "
                + taskList.getSize()
                + " task(s) in the list.";
        formatPrint(message);
    }

    /**
     * Prints a confirmation message when the user deletes a task.
     *
     * @param task {@link Task} that has been deleted from the {@link TaskList}
     * @param taskList taskList The current list of all tasks tracked by the backend.
     */
    public static void deleteTask(Task task, TaskList taskList) {
        String message = "Got it. I've deleted this task:\n"
                + printTask(task)
                + "\n"
                + "Now you have "
                + taskList.getSize()
                + " task(s) in the list.";
        formatPrint(message);
    }

    public static void clearList() {
        formatPrint("List has been cleared.");
    }

    public static void printMarked(Task task) {
        formatPrint("Nice!  I've marked this task as done:\n" + printTask(task));
    }

    public static void printUnmarked(Task task) {
        formatPrint("OK, I've marked this task as not done yet:\n" + printTask(task));
    }

    public static void descriptionEmptyError() {
        formatPrint("You have an empty description. Please try again.");
    }

    public static void markError() {
        formatPrint("This task has already been marked as done.");
    }

    public static void unmarkError() {
        formatPrint("This task has already been marked as not done.");
    }

    /**
     * Prints the output message when an unknown command is passed in by the user.
     */
    public static void unknownCommand() {
        String outputMessage = "I do not recognise this command, please check again!\n"
                + "Available commands are:\n"
                + Arrays.toString(CommandTypes.class.getEnumConstants()).toLowerCase();
        formatPrint(outputMessage);
    }

    public static void unknownNumber() {
        formatPrint("Please enter a valid number after the command!");
    }

    public static void emptyList() {
        formatPrint("You have no tasks in your list.");
    }

    /**
     * @param taskList The current list of all tasks tracked by the backend.
     */
    public static void outOfListBounds(TaskList taskList) {
        formatPrint("Invalid task number!\n"
                + "Please input a number between 1 and "
                + taskList.getSize());
    }

    public static void invalidNumberOfDetails() {
        formatPrint("Please give the correct amount of information for the command!");
    }

    public static void taskUnableToBeStoredInFile() {
        formatPrint("There was an error in adding the task to the storage file!");
    }

    /**
     * Prints an error message when the file cannot be loaded properly due to a corrupted text file.
     */
    public static void fileCorrupted() {
        formatPrint("The storage file has been corrupted.\n"
                + "Use the clear_tasks command to get rid of this!");
    }

    public static void invalidDate() {
        formatPrint("Please enter a valid date in dd-MM-yyy HHmm (24hr format)!.");
    }

    /**
     * Prints a list of all upcoming tasks from today.
     * These tasks must be {@link mortalreminder.tasks.TimedTask} and not have been marked as done.
     *
     * @param tasks The list of all tasks that are upcoming from today and is tracked by the backend.
     */
    public static void upcomingDeadlinesEvents(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            formatPrint("There are no upcoming tasks.");
        } else {
            StringBuilder output = new StringBuilder("The following tasks are due soon:\n");
            for (Task task : tasks) {
                output.append(printTask(task)).append("\n");
            }
            formatPrint(output.toString());
        }
    }

}
