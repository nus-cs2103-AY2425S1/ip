package cookie.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks with various operations for managing the tasks.
 */
public class TaskList {
    private ArrayList<Task> taskArrayList;

    /**
     * Constructs a new {@code TaskList} with the specified list of tasks.
     *
     * @param taskArrayList the initial list of tasks
     */
    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    /**
     * Adds a task to the list.
     *
     * @param task the {@code Task} to be added to the list
     */
    public void addTask(Task task) {
        taskArrayList.add(task);
    }

    /**
     * Deletes a task from the list based on the specified index.
     *
     * @param index the index of the task to be deleted, 1-based
     */
    public void delete(int index) {
        taskArrayList.remove(index - 1);
    }

    /**
     * Marks a task as completed based on the specified index. 1.
     * </p>
     *
     * @param index the index of the task to be marked as done, 1-based
     */
    public void markDone(int index) {
        taskArrayList.get(index - 1).markDone();
    }

    /**
     * Marks a task as not completed based on the specified index.
     *
     * @param index the index of the task to be marked as not done, 1-based
     */
    public void unmarkDone(int index) {
        taskArrayList.get(index - 1).unmarkDone();
    }

    /**
     * Returns a string representation of all tasks in the list.
     *
     * @return a string listing all tasks in the list
     */
    public String printTasks() {
        String string = "Here are the tasks in your list:\n";
        int count = 1;
        StringBuilder list = new StringBuilder();
        for (Task task: this.taskArrayList) {
            list.append(count++).append(": ").append(task.toString()).append("\n");
        }
        return string + list.toString();
    }

    /**
     * Returns the list of tasks.
     *
     * @return the {@code ArrayList} of tasks
     */
    public ArrayList<Task> getTaskArrayList() {
        return this.taskArrayList;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the number of tasks in the list
     */
    public int getSize() {
        return this.taskArrayList.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index the index of the task to be retrieved, 1-based
     * @return the {@code Task} at the specified index
     */
    public Task getTask(int index) {
        return this.taskArrayList.get(index - 1);
    }

    /**
     * Finds all tasks in the list with a description containing the specified keyword.
     *
     * @param string the keyword to search for in the task descriptions
     * @return an {@code ArrayList} of {@code Task} objects whose descriptions contain the keyword
     */
    public ArrayList<Task> findByKeyword(String string) {
        ArrayList<Task> arrayMatchKeyword = new ArrayList<>();

        for (Task task: this.taskArrayList) {
            if (task.getDescription().contains(string)) {
                arrayMatchKeyword.add(task);
            }
        }
        return arrayMatchKeyword;
    }
}
