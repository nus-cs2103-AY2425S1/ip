
package infinity.tasklist;

import infinity.infinityexception.InfinityException;
import infinity.task.Task;
import infinity.ui.Ui;

import java.util.ArrayList;

/**
 * This class handles the list of tasks actions. Each action method has to be called
 * with the current input already sanitised from the user.
 */
public class TaskList {

    public static final int MAX_SIZE = 100;
    
    private final Ui botUI;

    /** Maximum size of the task list */
    private ArrayList<Task> tasks = new ArrayList<>(MAX_SIZE);
    private int nextTaskIndex = 0;

    /**
     * Adds a task to the list of tasks.
     * 
     * @param <T> Type of Task that extends Task. Examples include ToDos, Events and Deadline.
     * @param task The task, T, to be added.
     * @throws InfinityException If the task list is full.
     */
    public <T extends Task> void addTask(T task) throws InfinityException {
        if (nextTaskIndex >= MAX_SIZE) {
            throw new InfinityException(
                    "I'm sorry, but I can't remember more tasks.");
        }

        tasks.add(task);
        nextTaskIndex++;

        botUI.botSays(String.format("I've added '%s'", task));
    }

    /**
     * Deletes a task from the list of tasks.
     * 
     * @param currentInput The string input from the user, but only the index as string.
     * @throws InfinityException If the task index is out of bounds or not a number.
     */
    public void deleteTask(String currentInput) throws InfinityException {
        int taskIndex = 0;

        try {
            taskIndex = Integer.parseInt(currentInput);
        } catch (NumberFormatException e) {
            throw new InfinityException("Hey! That's not a number");
        }

        taskIndex--;

        if (taskIndex >= nextTaskIndex || taskIndex < 0) {
            throw new InfinityException(
                    "Hmmm, you seem to have chose a task that doesn't exist. Nice try :)");
        } else {
            try {
                Task removedTask = tasks.remove(taskIndex);

                botUI.botSays(String.format(
                        "I've removed task %d:", taskIndex + 1));
                System.out.println(removedTask.toString());
            } catch (IndexOutOfBoundsException e) {
                throw new InfinityException(
                        "Hmmm, you seem to have chose a task that doesn't exist. Nice try :)");
            }
        }
    }

    /**
     * Marks a task as done.
     * 
     * @param currentInput The full string input from the user.
     * @throws InfinityException If the task index is out of bounds or not a number.
     */
    public void markTask(String currentInput) throws InfinityException {
        String[] words = currentInput.split(" ");
        int taskIndex;

        try {
            taskIndex = Integer.parseInt(words[1]) - 1;
            if (taskIndex >= nextTaskIndex || taskIndex < 0) {
                throw new InfinityException(
                        "Hmmm, I can't find that task. Please try again.");
            }
        } catch (NumberFormatException e) {
            throw new InfinityException("Hey! That's not a number");   
        }

        tasks.get(taskIndex).markAsDone();

        botUI.botSays(String.format(
                "I've marked task %d as done:\n%s", 
                taskIndex + 1, 
                tasks.get(taskIndex).toString()));
    }

    /**
     * Lists all the tasks in the list.
     */
    public void listTasks() {
        botUI.listTasks(this);
    }

    /**
     * Checks if the task list is empty.
     * 
     * @return True if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return nextTaskIndex == 0;
    }

    /**
     * Gets the list of tasks.
     * 
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    };

    /**
     * Constructor for the TaskList class.
     * 
     * @param initialTask The initial list of tasks. If empty, pass in an empty ArrayList.
     * @param botUI The initialised UI object to interact with the user.
     */
    @SuppressWarnings("unchecked")
    public TaskList(ArrayList initialTask, Ui botUI) {
        this.tasks = (ArrayList<Task>) initialTask;
        this.botUI = botUI;
        this.nextTaskIndex = initialTask.size();
    }
}