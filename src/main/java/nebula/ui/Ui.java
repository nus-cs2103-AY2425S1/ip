package nebula.ui;

import nebula.task.Task;
import nebula.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
//    private static final String DIVIDER = "---------------------------------------------------";
    private static  final String GREETING = "Hello! I'm Nebula, what can I do for you today?";
    private static  final String GOODBYE = "Bye! Hope to see you again soon :)";
    private static final String MARKED = "Nice! This task has successfully been marked:";
    private static final String UNMARKED = "Nice! This task has successfully been unmarked:";
    private static final String ALREADY_MARKED = "This task has already been marked!";
    private static final String ALREADY_UNMARKED = "This task has already been unmarked!";
    private static final String UNKNOWN_COMMAND = "OOPS! Unknown command! Please enter 'help'"
            + " for a list of commands I support!";
    private static final String UNKNOWN_TASK_NUMBER = "The mark/unmark/delete command " +
            "requires a task number!";
    private static final String NONEXISTENT_TASK_NUMBER = "The task number provided " +
            "does not exist.";
    private static final String UNKNOWN_DESCRIPTION = "The command requires a description!";
    private static final String UNKNOWN_DEADLINE = "The deadline task description " +
            "must contain '/by' followed by the deadline date.";
    private static final String UNKNOWN_EVENT_TIMING = "The event task description " +
            "must contain '/from' and '/to' with the respective start and end times.";
    private static final String ONE_KEYWORD = "Please enter exactly one keyword for the 'find' command.";
    private static final String NO_MATCHING_TASKS = "No matching tasks found.";

    /**
     * Returns the greeting message to be displayed to the user
     *
     * @return A string containing the greeting message
     */
    public String greeting() {
        return GREETING;
    }

    /**
     * Returns the goodbye message to be displayed to the user
     *
     * @return A string containing the goodbye message
     */
    public String goodbye() {
        return GOODBYE;
    }

    /**
     * Returns a message indicating that a new task has been added
     *
     * @param task The task that was added
     * @return A string containing the message that a task was added
     * with the task description
     */
    public String displayAddedTask(Task task) {
        return "Got it! I've added this task:" + "\n"
                + "  " + task.toString() + "\n" + "Now you have "
                + TaskList.getTaskListLength()
                + (TaskList.getTaskListLength() == 1
                    ? " task "
                    : " tasks ") + "in the list.";
    }

    /**
     * Returns a string representation of the current task list
     *
     * @return A string containing the formatted list of tasks.
     * If the list is empty, a message indicating that the list is empty is returned
     */
    public String displayList() {
        String displayList = "Here are the tasks in your list:" + "\n";
        int taskLength = TaskList.getTaskListLength();

        ArrayList<Task> list = TaskList.getTaskList();

        if(taskLength == 0) {
            return "Your task list is empty!";
        }
        for (int i = 0; i < taskLength; i++) {
            displayList += (i + 1) + ". " + list.get(i).toString();
            if(i < taskLength - 1) {
                displayList += "\n";
            }
        }
        return displayList;
    }

    /**
     * Returns a message indicating that a task has been marked as completed
     *
     * @param task The task that has been marked as completed
     * @return A string containing the message "marked" along with the task
     * description and its status icon
     */
    public String displayMarkedTask(Task task) {
        return MARKED + "\n" + "  " + task.toString();
    }

    /**
     * Returns a message indicating that a task was already marked as completed
     *
     * @return A string containing a message indicating that the task was already marked
     */
    public String displayAlreadyMarkedTask() {
        return ALREADY_MARKED;
    }

    /**
     * Returns a message indicating that a task has been unmarked
     *
     * @param task The task that has been unmarked
     * @return A string containing the message "unmarked" along with the task
     * description and its status icon
     */
    public String displayUnmarkedTask(Task task) {
        return UNMARKED + "\n" + "  " + task.toString();
    }

    /**
     * Returns a message indicating that a task was already unmarked
     *
     * @return A string containing a message indicating that the task was already unmarked
     */
    public String displayAlreadyUnmarkedTask() {
        return ALREADY_UNMARKED;
    }

    /**
     * Displays a message confirming that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @return A string message confirming the deletion of the task and showing the current number of tasks.
     */
    public String displayDeletedTask(Task task) {
        return "Noted. I've removed this task:" + "\n" + " "
                + task.toString() + "\n" + "Now you have " + TaskList.getTaskListLength()
                + (TaskList.getTaskListLength() == 1 ? " task " : " tasks ")
                + "in the list.";
    }

    /**
     * Displays an error message when an unknown command is entered.
     *
     * @return A string message indicating that the command is unknown.
     */
    public String displayUnknownCommandException() {
        return UNKNOWN_COMMAND;
    }

    /**
     * Displays an error message when the task number entered by the user is unknown.
     *
     * @return A string message indicating that the task number is unknown.
     */
    public String displayUnknownTaskNumberException() {
        return UNKNOWN_TASK_NUMBER;
    }

    /**
     * Displays an error message when the user enters a task number that does not exist.
     *
     * @return A string message indicating that the task number does not exist.
     */
    public String displayNonexistentTaskNumberException() {
        return NONEXISTENT_TASK_NUMBER;
    }

    /**
     * Displays an error message when the task description is unknown or missing.
     *
     * @return A string message indicating that the task description is unknown.
     */
    public String displayUnknownMessageException() {
        return UNKNOWN_DESCRIPTION;
    }

    /**
     * Displays an error message when the deadline of a task is unknown or improperly formatted.
     *
     * @return A string message indicating that the deadline is unknown or incorrect.
     */
    public String displayUnknownDeadlineException() {
        return UNKNOWN_DEADLINE;
    }

    /**
     * Displays an error message when the event timing is unknown or improperly formatted.
     *
     * @return A string message indicating that the event timing is unknown or incorrect.
     */
    public String displayUnknownEventTimingException() {
        return UNKNOWN_EVENT_TIMING;
    }

    public String displayNoMatchingTasks() {return NO_MATCHING_TASKS;}

    public String displayMatchingTasks(ArrayList<Task> matchingTasks) {
        String displayList = "Here are the matching tasks in your list:" + "\n";

        for (int i = 0; i < matchingTasks.size(); i++) {
            displayList += (i + 1) + ". " + matchingTasks.get(i).toString();
            if (i < matchingTasks.size() - 1) {
                displayList += "\n"; // Add a newline between tasks but not after the last task
            }
        }
        return displayList;
    }

    public String displayCommands() {
        String commands = "";
        commands += "These are the commands I support!\n\n";
        commands += "1. list - Lists the tasks in your task list\n";
        commands += "2. bye - Ends the conversation with the chatbot\n";
        commands += "3. todo - Adds a Todo Task to your task list (Format: Todo <task name>)\n";
        commands += "4. deadline - Adds a Deadline Task to your task list "
                + "(Format: deadline <task name> /by <due date in dd-mm-yyyy hh:mm>)\n";
        commands += "5. Event - Adds a Event Task to your task list "
                + "(Format: event <task name> /from <due date in dd-mm-yyyy hh:mm>"
                + " /to <due date in dd-mm-yyyy hh:mm>)\n";
        commands += "7. mark - Marks a task as completed (Format: mark <task number>)\n";
        commands += "8. unmark - Marks a task as not completed (Format: unmark <task number>)\n";
        commands += "9. delete - Deletes a task from the task list (Format: delete <task number>)\n";
        commands += "10. find - Finds tasks that contain a specific keyword (Format: find <keyword>)\n";
        commands += "11. help - Shows a list of commands this chatbot supports\n";
        return commands;
    }

    public String displayOneKeywordException() {
        return ONE_KEYWORD;
    }

    /**
     * Reads a command input by the user from the console.
     *
     * @return The command string entered by the user.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        return command;
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
