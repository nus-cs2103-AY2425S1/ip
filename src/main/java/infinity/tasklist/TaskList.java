
package infinity.tasklist;

import java.util.ArrayList;

import infinity.infinityexception.InfinityException;
import infinity.task.Task;
import infinity.ui.Ui;

/**
 * This class handles the list of tasks actions. Each action method has to be called
 * with the current input already sanitised from the user.
 */
public class TaskList {

    /** Max size of the Task List. */
    public static final int MAX_SIZE = 100;
    /** Bot reply for max tasks */
    private static final String BOT_MAX_TASKS =
            "I'm sorry, but I can't remember more tasks.";
    /** Bot reply for an invalid index */
    private static final String BOT_INVALID_INDEX =
            "Hmmm, you seem to have chose a task that doesn't exist. Nice try :)";
    /** Bot reply for unable to find */
    private static final String BOT_UNABLE_TO_FIND =
            "Hmmm, I can't find that task. Please try again.";
    /** Bot reply for using find method with 0 found output */
    private static final String BOT_FIND_ZERO_OUTPUT =
            "\nStrange, I can't find any tasks with that keyword...";
    /** Bot reply for using find method */
    private static final String BOT_FINDS =
            "Alright, alright, let me find that for you...\n";
    /** Bot reply for not a number */
    private static final String BOT_NOT_A_NUMBER =
            "Hey! That's not a number";

    private ArrayList<Task> tasks = new ArrayList<>(MAX_SIZE);
    private int tasksSize;

    /**
     * Constructor for the TaskList class.
     *
     * @param initialTask The initial list of tasks. If empty, pass in an empty ArrayList.
     */
    public TaskList(ArrayList<Task> initialTask) {
        this.tasks = initialTask;
        this.tasksSize = initialTask.size();
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param <T> Type of Task that extends Task. Examples include ToDos, Events and Deadline.
     * @param task The task, T, to be added.
     * @return The bot output.
     * @throws InfinityException If the task list is full.
     */
    public <T extends Task> String addTask(T task) throws InfinityException {
        if (nextTaskIndex >= MAX_SIZE) {
            throw new InfinityException(Ui.botSays(BOT_MAX_TASKS));
        }

        tasks.add(task);
        tasksSize++;

        assert tasks.size() > 0 && tasks.size() <= MAX_SIZE : "Task size should be in range";

        return Ui.botSays(String.format("I've added '%s'", task));
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param currentInput The string input from the user, but only the index as string.
     * @return The bot output.
     * @throws InfinityException If the task index is out of bounds or not a number.
     */
    public String deleteTask(String currentInput) throws InfinityException {
        int taskIndex;

        try {
            taskIndex = Integer.parseInt(currentInput);
        } catch (NumberFormatException e) {
            throw new InfinityException(Ui.botSays(BOT_NOT_A_NUMBER));
        }

        taskIndex--;

        if (taskIndex >= nextTaskIndex || taskIndex < 0) {
            throw new InfinityException(Ui.botSays(BOT_INVALID_INDEX));
        } else {
            try {
                Task removedTask = tasks.remove(taskIndex);
                tasksSize--;

                assert tasks.size() >= 0 : "Task size should be at least 0";

                return Ui.botSays(String.format(
                        "I've removed task %d:\n%s", taskIndex + 1, removedTask.toString()));
            } catch (IndexOutOfBoundsException e) {
                throw new InfinityException(Ui.botSays(BOT_INVALID_INDEX));
            }
        }
    }

    /**
     * Marks a task as done.
     *
     * @param currentInput The full string input from the user.
     * @return The bot output.
     * @throws InfinityException If the task index is out of bounds or not a number.
     */
    public String markTask(String currentInput) throws InfinityException {
        String[] words = currentInput.split(" ");
        int taskIndex;

        try {
            taskIndex = Integer.parseInt(words[1]) - 1;
            if (taskIndex >= nextTaskIndex || taskIndex < 0) {
                throw new IndexOutOfBoundsException();
            }
            tasks.get(taskIndex).markAsDone();
        } catch (NumberFormatException e) {
            throw new InfinityException(Ui.botSays(BOT_NOT_A_NUMBER));
        } catch (IndexOutOfBoundsException e) {
            throw new InfinityException(Ui.botSays(BOT_UNABLE_TO_FIND));
        }

        assert tasks.get(taskIndex).toString().charAt(4) == 'X' : "Task should be marked as X";

        return Ui.botSays(String.format(
                "I've marked task %d as done:\n%s",
                taskIndex + 1,
                tasks.get(taskIndex).toString()));
    }

    /**
     * Lists all the tasks in the list.
     *
     * @return The bot output.
     */
    public String listTasks() {
        return Ui.listTasks(this);
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasksSize == 0;
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds tasks with a keyword and lists them.
     *
     * @param keyword The keyword to search for in the tasks.
     * @return The bot output.
     */
    public String findTasks(String keyword) {
        int i = 1;

        StringBuilder botOutput = new StringBuilder(Ui.botSays(BOT_FINDS, false));

        for (Task task : tasks) {
            if (task.findTask(keyword)) {
                botOutput.append(Ui.listTask(task, i));
                botOutput.append("\n");
                i++;
            }
        }

        if (i == 1) {
            botOutput.append(BOT_FIND_ZERO_OUTPUT);
        }

        return botOutput.toString();
    }
}
