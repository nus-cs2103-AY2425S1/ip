package pixel;

import java.util.ArrayList;

/**
 * Contains the task list
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor method for TaskList
     * @param tasks ArrayList of Task to contain all tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the Task at a specified index
     *
     * @param index index of Task to obtain
     * @return Task at given index position
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Adds the task to the ArrayList
     *
     * @param newTask Task to be added
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Deletes the tasks from the ArrayList
     *
     * @param index index of Task to be removed
     */
    public void deleteTask(int index) {
        Task removedTask = getTask(index);
        tasks.remove(removedTask);
    }

    public void printMatchingTasks(String searchKey) {
        TaskList matchingTasks = new TaskList(new ArrayList<>());
        for (Task currentTask : tasks) {
            if (currentTask.getDescription().contains(searchKey)) {
                matchingTasks.addTask(currentTask);
            }
        }
        if (matchingTasks.getSize() > 0) {
            matchingTasks.printTasks();
        } else {
            System.out.println("\t" + "No tasks match your search key. Try another search key.");
        }
    }

    /**
     * Prints all tasks in the ArrayList
     */
    public void printTasks() {
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println("\t" + i + "." + tasks.get(i-1));
        }
    }

    /**
     * Returns the list of tasks as an ArrayList
     *
     * @return ArrayList of Task
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns size of ArrayList
     *
     * @return integer representing size of ArrayList
     */
    public int getSize() {
        return tasks.size();
    }
}
