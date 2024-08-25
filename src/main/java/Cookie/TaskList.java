package Cookie;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskArrayList;

    /**
     * Constructor for TaskList class.
     */
    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    /**
     * Adds a task to the ArrayList.
     *
     * @param task Task to be added to the ArrayList.
     */
    public void addTask(Task task) {
        taskArrayList.add(task);
    }

    /**
     * Deletes a Task from the ArrayList
     *
     * @param index Index of task to be deleted.
     */
    public void delete(int index) {
        taskArrayList.remove(index - 1);
    }

    /**
     *  Marks a task a done.
     *
     * @param index Index of task to be marked done.
     */
    public void markDone(int index) {
        taskArrayList.get(index - 1).markDone();
    }

    /**
     * Marks a task as not done.
     *
     * @param index Index of task to be marked not done.
     */
    public void unmarkDone(int index) {
        taskArrayList.get(index - 1).unmarkDone();
    }

    /**
     * Prints all the tasks in the ArrayList.
     *
     * @return A String representation of all the task in the list.
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
     * Gets the ArrayList.
     */
    public ArrayList<Task> getTaskArrayList() {
        return this.taskArrayList;
    }

    /**
     * Gets the size of the ArrayList.
     */
    public int getSize() {
        return this.taskArrayList.size();
    }

    /**
     * Gets the task at a given index.
     *
     * @param index Index of task to be returned.
     * @return Task at index - 1.
     */
    public Task getTask(int index) {
        return this.taskArrayList.get(index - 1);
    }

    /**
     * Finds all task with a description containing the keyword.
     *
     * @param string Keyword to be searched.
     * @return ArrayList of tasks that has the keyword in the description.
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
