package denim;

import java.util.ArrayList;
import java.util.List;

import denim.tasks.Task;

/**
 * Represents a list of tasks that can be managed by the user.
 * This class provides methods to add, delete, mark, unmark, and retrieve tasks
 * from the task list, as well as to check for valid task indices.
 */
public class TaskList {
    private static final int INITIAL_CAPACITY = 100;
    private final ArrayList<Task> taskList;

    /**
     * Constructs a new TaskList with an initial capacity of 100 tasks.
     */
    public TaskList() {
        taskList = new ArrayList<>(INITIAL_CAPACITY);
    }

    /**
     * Adds a task to the end of the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Adds a task at the specified index in the task list.
     * Shifts the task currently at that position (if any) and any subsequent tasks to the right.
     *
     * @param index The index at which the task is to be inserted.
     * @param task  The task to be added.
     */
    public void addTaskAtIndex(int index, Task task) {
        taskList.add(index, task);
    }

    /**
     * Deletes the task at the specified index from the task list.
     * Shifts any subsequent tasks to the left.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTaskAtIndex(int index) {
        taskList.remove(index);
    }

    /**
     * Finds all the tasks with the given keyword in the TaskList and returns a new TaskList
     * with all the tasks found.
     *
     * @param keywords an array of String keywords to find.
     */
    public TaskList findTasks(String ... keywords) {
        TaskList matchingTasks = new TaskList();
        for (Task task: taskList) {
            for (String keyword: keywords) {
                if (task.getDescription().contains(keyword)) {
                    matchingTasks.addTask(task);
                }
            }
        }
        return matchingTasks;
    }

    /**
     * Returns the task at the specified index in the task list.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        assert index >= 0 && index < taskList.size() : "IndexOutOfBoundsAssertion";
        return taskList.get(index);
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markTask(int index) {
        assert index >= 0 && index < taskList.size() : "IndexOutOfBoundsAssertion";
        this.taskList.get(index).setIsDone(true);
    }

    /**
     * Unmarks the task at the specified index, marking it as not done.
     *
     * @param index The index of the task to be unmarked.
     */
    public void unmarkTask(int index) {
        assert index >= 0 && index < taskList.size() : "IndexOutOfBoundsAssertion";
        taskList.get(index).setIsDone(false);
    }

    /**
     * Checks if the specified index is valid within the task list.
     * A valid index is one that is non-negative and less than the size of the task list.
     *
     * @param trueIndex The index to be checked.
     * @return True if the index is valid, false otherwise.
     */
    public boolean isValidIndex(int trueIndex) {
        return trueIndex >= 0 && trueIndex < getTaskListSize();
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getTaskListSize() {
        return taskList.size();
    }

    /**
     * Returns a string representation of the task list.
     * If the task list is empty, it returns a message indicating that the list is empty.
     * Otherwise, it returns each task in the list numbered and formatted on a new line.
     *
     * @return A string representation of the task list.
     */
    public String printList() {
        if (taskList.isEmpty()) {
            return "Your Task List is Empty.\n";
        }

        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            stringList.add(String.format("%d %s", i + 1, this.getTask(i)));
        }

        String returnListMessage = String.join("\n", stringList);
        return returnListMessage;
    }
}
