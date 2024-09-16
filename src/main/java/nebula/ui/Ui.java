package nebula.ui;

import nebula.task.Task;
import nebula.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "---------------------------------------------------";
    private static  final String GREETING = "Hello! I'm Nebula, what can I do for you today?";
    private static  final String GOODBYE = "Bye! Hope to see you again soon :)";
    private static final String MARKED = "Nice! This task has successfully been marked:";
    private static final String UNMARKED = "Nice! This task has successfully been unmarked:";
    private static final String ALREADY_MARKED = "This task has already been marked!";
    private static final String ALREADY_UNMARKED = "This task has already been unmarked!";
    private static final String UNKNOWN_COMMAND = "OOPS! Unknown command!";
    private static final String UNKNOWN_TASK_NUMBER = "The mark/unmark/delete command requires a task number.";
    private static final String NONEXISTENT_TASK_NUMBER = "The task number provided does not exist.";
    private static final String UNKNOWN_DESCRIPTION = "The command requires a description.";
    private static final String UNKNOWN_DEADLINE = "The deadline task description must contain '/by' followed by the deadline date.";

    private static final String UNKNOWN_EVENT_TIMING = "The event task description must contain '/from' and '/to' with the respective start and end times.";

    private static final String ONE_KEYWORD = "Please enter exactly one keyword for the 'find' command.";

    private static final String NO_MATCHING_TASKS = "No matching tasks found.";

    /**
     * Returns the greeting message to be displayed to the user
     *
     * @return A string containing the greeting message
     */
    public String greeting() {
        return DIVIDER + "\n" + GREETING + "\n" + DIVIDER;
    }

    /**
     * Returns the goodbye message to be displayed to the user
     *
     * @return A string containing the goodbye message
     */
    public String goodbye() {
        return DIVIDER + "\n" + GOODBYE;
    }

    /**
     * Returns a message indicating that a new task has been added
     *
     * @param task The task that was added
     * @return A string containing the message that a task was added with the task description
     */
    public String displayAddedTask(Task task) {
        return DIVIDER + "\n" + "Got it! I've added this task:" + "\n"
                + "  " + task.toString() + "\n" + "Now you have " + TaskList.getTaskListLength()
                + (TaskList.getTaskListLength() == 1 ? " task " : " tasks ") + "in the list.";
    }

    /**
     * Returns a string representation of the current task list
     *
     * @return A string containing the formatted list of tasks. If the list is empty, a message indicating that the list is empty is returned
     */
    public String displayList() {
        String displayList = DIVIDER + "\n" + "Here are the tasks in your list:" + "\n";
        int taskLength = TaskList.getTaskListLength();
        ArrayList<Task> list = TaskList.getTaskList();
        if(taskLength == 0) {
            return DIVIDER + "\n" + "Your task list is empty!";
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
     * @return A string containing the message "marked" along with the task description and its status icon
     */
    public String displayMarkedTask(Task task) {
        return DIVIDER + "\n" + MARKED + "\n" + "  " + task.toString();
    }

    /**
     * Returns a message indicating that a task was already marked as completed
     *
     * @return A string containing a message indicating that the task was already marked
     */
    public String displayAlreadyMarkedTask() {
        return DIVIDER + "\n" + ALREADY_MARKED;
    }

    /**
     * Returns a message indicating that a task has been unmarked
     *
     * @param task The task that has been unmarked
     * @return A string containing the message "unmarked" along with the task description and its status icon
     */
    public String displayUnmarkedTask(Task task) {
        return DIVIDER + "\n" + UNMARKED + "\n" + "  " + task.toString();
    }

    /**
     * Returns a message indicating that a task was already unmarked
     *
     * @return A string containing a message indicating that the task was already unmarked
     */
    public String displayAlreadyUnmarkedTask() {
        return DIVIDER + "\n" + ALREADY_UNMARKED;
    }

    public String displayDeletedTask(Task task) {
        return DIVIDER + "\n" + "Noted. I've removed this task:" + "\n" + " " + task.toString()
                + "\n" + "Now you have " + TaskList.getTaskListLength()
                + (TaskList.getTaskListLength() == 1 ? " task " : " tasks ") + "in the list.";
    }

    public String displayUnknownCommandException() {
        return DIVIDER + "\n" + UNKNOWN_COMMAND;
    }

    public String displayUnknownTaskNumberException() {
        return DIVIDER + "\n" + UNKNOWN_TASK_NUMBER;
    }

    public String displayNonexistentTaskNumberException() {
        return DIVIDER + "\n" + NONEXISTENT_TASK_NUMBER + "\n" + DIVIDER;
    }

    public String displayUnknownMessageException() {
        return DIVIDER + "\n" + UNKNOWN_DESCRIPTION;
    }

    public String displayUnknownDeadlineException() {
        return DIVIDER + "\n" + UNKNOWN_DEADLINE;
    }

    public String displayUnknownEventTimingException() {
        return DIVIDER + "\n" + UNKNOWN_EVENT_TIMING;
    }

    public String displayNoMatchingTasks() {return DIVIDER + "\n" + NO_MATCHING_TASKS;}

    public String displayMatchingTasks(ArrayList<Task> matchingTasks) {
        String displayList = DIVIDER + "\n"; // Add a divider at the beginning

        displayList += "Here are the matching tasks in your list:" + "\n";

        for (int i = 0; i < matchingTasks.size(); i++) {
            displayList += (i + 1) + ". " + matchingTasks.get(i).toString();
            if (i < matchingTasks.size() - 1) {
                displayList += "\n"; // Add a newline between tasks but not after the last task
            }
        }
        return displayList;
    }

    public String displayOneKeywordException() {
        return DIVIDER + "\n" + ONE_KEYWORD;
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        return command;
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
