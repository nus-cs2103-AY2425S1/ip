package tira;
import java.io.PrintWriter;
import java.util.ArrayList;

import tira.task.Deadline;
import tira.task.Event;
import tira.task.Task;
import tira.task.TaskList;
import tira.task.ToDo;

/**
 * The Ui class handles the user's commands and interacts with the user.
 * It is responsible for reading user input, printing information, and displaying errors.
 * This class serves as an input for TaskList, Storage, and Tira Main.
 */
public class Ui {
    private StringBuilder outMessage;
    private final PrintWriter printer = new PrintWriter(System.out);

    /**
     * Constructs a Ui instance with a Scanner for reading user input.
     */
    // Solution using StringBuilder inspired by
    // https://github.com/hansneddyanto/ip/blob/master/src/main/java/hana/Ui.java
    public Ui() {
        this.outMessage = new StringBuilder();
    }

    /**
     * Gets the current output message that will be displayed in the GUI
     * and clears the outMessage in the Ui to prevent duplicates.
     * @return The current string output message.
     */
    public String getOutMessage() {
        String outString = this.outMessage.toString();
        this.outMessage = new StringBuilder();
        return outString;

    }

    /**
     * Displays a welcome message to the user.
     */
    public String showWelcome() {
        return "MIAO! I'm TIRAMISU THE CAT (TIRA)\n"
                + "What can I do for you today, miao?\n";
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showBye() {
        outMessage.append("Bye. Come back with treats, MIAO!");
    }

    /*
    Solution below inspired by https://github.com/hansneddyanto/ip/blob/master/src/main/java/hana/Ui.java
     */
    /**
     * Shows the current list of tasks to the user.
     *
     * @param taskList The TaskList containing the tasks to display.
     */
    public String showTaskList(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        outMessage.append("HERE ARE THE CURRENT TASKS:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            outMessage.append((i + 1)
                    + ". "
                    + currTask
                    + "\n");
        }
        String outString = this.outMessage.toString();
        this.outMessage = new StringBuilder();
        return outString;
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The Task that has been marked as done.
     */
    public void showMarkTask(Task task) {
        outMessage.append("NYA! Good job on this task:"
                + "\n"
                + task
                + "\n");
    }

    /**
     * Displays a message indicating that a task has been unmarked.
     *
     * @param task The Task that has been unmarked.
     */
    public void showUnmarkTask(Task task) {
        outMessage.append("MRAWWW! Don't forget to return to this task:"
                + "\n"
                + task
                + "\n");
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param task The Task that has been added.
     * @param taskSize The current size of the task list.
     */
    public void showAddTask(Task task, int taskSize) {
        outMessage.append("Miao! Got it. I've added this task to my cat brain:\n"
                + task
                + "\nNow you have "
                + taskSize
                + " task(s) in the list!");
    }

    /**
     * Displays a message indicating that a task has been deleted from the list.
     *
     * @param task The Task that has been removed.
     * @param taskSize The current size of the task list.
     */
    public void showDelete(Task task, int taskSize) {
        outMessage.append("Noted, Miao! I've removed this task:\n"
                + task
                + "\nNow you have "
                + taskSize
                + " task(s) in the list!");
    }

    /**
     * Displays an error message when there is an issue loading the file.
     */
    public void showLoadingError() {
        outMessage.append("Oh no... There is an error while loading the file! ");
    }

    /**
     * Displays a message indicating that no matching task was found.
     */
    public void showNoMatchingTask() {
        outMessage.append("Miao... No such task... Sorry!");
    }

    /**
     * Displays a list of tasks that match a search query.
     *
     * @param tasks An ArrayList of matching tasks.
     */
    public void showMatchingTasks(ArrayList<Task> tasks) {
        outMessage.append("Miao!!!! I found the tasks in my cat brain! They are:\n");
        for (Task task: tasks) {
            outMessage.append(task + "\n");
        }
    }

    /**
     * Count statistics for the given list of tasks.
     *
     * @param tasks TaskList of Tasks.
     * @return Statistics object that has performed the calculations.
     */
    private Statistics countStats(ArrayList<Task> tasks) {
        Statistics stats = new Statistics();
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                stats.addTaskCount("deadline");
                stats.addMarkOrUnmarkCount(task.getIsDone(), "deadline");
            } else if (task instanceof Event) {
                stats.addTaskCount("event");
                stats.addMarkOrUnmarkCount(task.getIsDone(), "event");
            } else if (task instanceof ToDo) {
                stats.addTaskCount("todo");
                stats.addMarkOrUnmarkCount(task.getIsDone(), "todo");
            }
        }
        return stats;
    }

    /**
     * Displays the statistics of the current TaskList to the GUI
     *
     * by storing the statistics string in the getOutMessage method.
     * @param taskList The updated TaskList of tasks.
     */
    public void showStatistics(ArrayList<Task> taskList) {
        Statistics stats = countStats(taskList);
        outMessage.append("OK MIAO! Here's your TaskList statistics:\n");
        outMessage.append("Number of tasks in the list: " + stats.getTotalTasks() + "\n"
                + "\nTODOS\nToDos: " + stats.toDoCount
                + "\nMarked ToDos: " + stats.markedToDoCount
                + "\nUnmarked ToDos: " + stats.unmarkedToDoCount + "\n"
                + "\nDEADLINES\nDeadlines: " + stats.deadlineCount
                + "\nMarked Deadlines: " + stats.markedDeadlineCount
                + "\nUnmarked Deadlines: " + stats.unmarkedDeadlineCount + "\n"
                + "\nEVENTS\nEvents: " + stats.eventCount
                + "\nMarked Events: " + stats.markedEventCount
                + "\nUnmarked Events: " + stats.unmarkedEventCount + "\n"
                + "\nTotal number of Marked tasks:" + stats.getTotalMarkedTasks() + "\n"
                + "Total number of Unmarked tasks:" + stats.getTotalUnmarkedTasks() + "\n"
                + "% of Marked tasks: " + stats.getMarkedPercentage() + "\n"
        );
    }
}
