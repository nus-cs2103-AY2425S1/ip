package Talky;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Class that acts as a wrapper for list of Tasks.
 */
public class TaskList {
    private static ArrayList<Task> taskList;

    /**
     * Constructor to initialize empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor to initialize TaskList based off given arraylist
     *
     * @param taskList ArrayList of tasks
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns TaskList formatted into ui list format.
     *
     * @return tasklist as ui list format.
     */

    public String toListFormat() {
        String result = "";
        int rank = 1;
        for (Task task : taskList) {
            result += rank + "." + task.toString() + "\n";
            rank++;
        }
        return result;
    }

    /**
     * Returns TaskList formatted into SaveData line.
     *
     * @return tasklist as SaveData line.
     */
    public String toSaveFormat() {
        String saveFormat = "";
        for (Task task : taskList) {
            saveFormat += (task.toSaveFormat() + "\n");
        }
        return saveFormat;
    }

    /**
     * Marks task of given index.
     * Returns name of the marked task.
     *
     * @param index index of task to mark.
     * @param isMarked mark status to set.
     * @return name of marked task.
     */
    public String markTask(int index, boolean isMarked) {
        Task changedTask = taskList.get(index);
        changedTask.setMark(isMarked);
        return changedTask.getName();
    }

    /**
     * Adds new ToDo of given name.
     *
     * @param name Name of ToDo
     */
    public void addToDo(String name) {
        taskList.add(new ToDo(name));
    }

    /**
     * Add new Deadline of given name and datetime by
     *
     * @param name Name of deadline
     * @param by LocalDateTime of by
     */
    public void addDeadline(String name, LocalDateTime by) {
        taskList.add(new Deadline(name, by));
    }

    /**
     * Adds new Event of given name and datetime from and to
     *
     * @param name Name of Event.
     * @param from LocalDateTime of from.
     * @param to LocalDateTime of to.
     */
    public void addEvent(String name, LocalDateTime from, LocalDateTime to) {
        taskList.add(new Event(name, from, to));
    }

    /**
     * Delete task of given index
     *
     * @param index Index of task to delete.
     * @return name of deleted task.
     */
    public String deleteTask(int index) {
        Task changedTask = taskList.get(index);
        taskList.remove(index);
        return changedTask.getName();
    }

    public TaskList find(String keyword) {
        TaskList validTasks = new TaskList();
        for (Task task: taskList) {
            String taskName = task.getName();
            if (taskName.contains(keyword)) {
                validTasks.addTask(task);
            }
        }
        return validTasks;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }
}
