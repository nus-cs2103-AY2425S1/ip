package killua.ui;

import java.time.LocalDate;

import killua.task.Task;
import killua.util.TaskList;

/**
 * Represents the user interface of the Killua task manager.
 * It handles the display of messages and reading of user input.
 */
public class Ui {

    private static final String WELCOME_MESSAGE = """
        Welcome to Killua Task Manager!
        Here are some commands you can use:
        \n
        -list/ls - List all tasks
        -mark/m <task number> - Mark a task as done
        -unmark/um <task number> - Mark a task as not done yet
        -delete/del <task number> - Delete a task
        -todo <description> - Add a new todo task
        -deadline/ddl <description> /by <yyyy-mm-dd[ hh:mm]> - Add a new deadline task
        -event/eve <description> /from <yyyy-mm-dd[ hh:mm]> /to <yyyy-mm-dd[ hh:mm]> - Add a new event task
        -on <yyyy-mm-dd> - List tasks on date
        -find <keyword> - Find task with keyword
        -bye - Exit the application""";
    private static final String ADD_TASK_MESSAGE = "Got it. I've added this task:\n";
    private static final String DELETE_TASK_MESSAGE = "OK, I've deleted this task:\n";
    private static final String EXIT_MESSAGE = "See you later!";
    private static final String TASK_EMPTY_MESSAGE = "Your task list is empty.";
    private static final String SHOW_TASK_MESSAGE = "Here are the tasks in your list:\n";
    private static final String MARK_TASK_MESSAGE = "Nice! I've marked this task as done:\n";
    private static final String UNMARK_TASK_MESSAGE = "OK, I've marked this task as not done yet:\n";
    private static final String TASK_NOT_FOUND_MESSAGE = "No tasks found for this date.\n";
    private static final String NO_MATCHING_TASK_MESSAGE = "No matching task in your list!";
    private static final String SHOW_MATCHED_TASK_MESSAGE = "Here are the Matching tasks in your list:";
    /**
     * Constructs Ui instance.
     */
    public Ui() {}

    /**
     * Displays a welcome message and a list of available commands.
     */
    public String welcomeUser() {
        return WELCOME_MESSAGE;
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param task The task that was added.
     * @param taskCount The total number of tasks in the list after the addition.
     */
    public String showTaskAdded(Task task, int taskCount) {
        return ADD_TASK_MESSAGE + task + "\nNow you have " + taskCount + " tasks in the list.";
    }

    /**
     * Displays a message indicating that a task has been deleted from the list.
     *
     * @param task The task that was deleted.
     */
    public String showTaskDeleted(Task task) {
        return DELETE_TASK_MESSAGE + task;
    }

    /**
     * Displays a farewell message when the application is exiting.
     */
    public String showExitMessage() {
        return EXIT_MESSAGE;
    }

    /**
     * Displays the current list of tasks.
     *
     * @param tasks The task list to be displayed.
     */
    public String showTaskList(TaskList tasks) {
        String message;
        if (tasks.isEmpty()) {
            message = TASK_EMPTY_MESSAGE;
        } else {
            message = SHOW_TASK_MESSAGE + tasks.getTasksString();
        }
        return message;
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public String showTaskMarked(Task task) {
        return MARK_TASK_MESSAGE + task;
    }

    /**
     * Displays a message indicating that a task has been marked as not done yet.
     *
     * @param task The task that was marked as not done yet.
     */
    public String showTaskUnmarked(Task task) {
        return UNMARK_TASK_MESSAGE + task;
    }

    /**
     * Displays tasks that are scheduled for a specific date.
     *
     * @param tasks The task list containing the tasks to be displayed.
     * @param date The date for which tasks are to be displayed.
     */
    public String showTasksOnDate(TaskList tasks, LocalDate date) {
        StringBuilder sb = new StringBuilder();
        sb.append("Tasks on ").append(date).append(":\n");

        boolean hasTasks = false;
        for (Task task : tasks.getTasksOnDate(date).getTasks()) {
            sb.append("  ").append(task).append("\n");
            hasTasks = true;
        }

        if (!hasTasks) {
            sb.append(TASK_NOT_FOUND_MESSAGE);
        }

        return sb.toString();
    }

    /**
     * Displays the tasks that match a specified keyword.
     * Prints a message to indicate whether any matching tasks were found.
     * If matching tasks are found, prints each matching task to the console.
     * Otherwise, prints a message indicating that no matching tasks were found.
     *
     * @param tasks The TaskList containing the tasks to be displayed.
     */
    public String showMatchedTask(TaskList tasks) {
        String message;
        if (tasks.isEmpty()) {
            message = NO_MATCHING_TASK_MESSAGE;
        } else {
            message = SHOW_MATCHED_TASK_MESSAGE + tasks.getTasksString();
        }
        return message;
    }
}
