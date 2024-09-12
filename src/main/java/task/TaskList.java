package task;

import task.Task;
import prince.Prince;
import exception.IncompleteDescException;
import exception.UnknownWordException;

import java.util.ArrayList;

/**
 * Manages a list of tasks in an arrayList and provides methods to interact with the task list.
 *
 * The TaskList class allows for adding, removing, and retrieving tasks from the list.
 */

public class TaskList {

    private static ArrayList<Task> list;

    /**
     * Constructs a TaskList with the specified initial list of tasks.
     * @param tasks
     */

    public TaskList(ArrayList<Task> tasks) {
        list = tasks;
    }

    /**
     * Getter method for list of tasks
     * @return tasks
     */

    public static ArrayList<Task> getList() {
        return list;
    }

    /**
     * Adds a task to the task list.
     * @param task
     */

    public static void addTask(Task task){
        // add task to the List
        // return a string
        assert task != null : "Task should not be null";

        list.add(task);
        //return "added: " + task.getDescription();
    }

    /**
     * Removes a task from the task list based on the given index.
     * @param num
     */

    public static void delTask(int num){
        // add task to the List
        // return a string
        if (num >= 0 && num < list.size()) {
            list.remove(num);
        }
        //return "added: " + task.getDescription();
    }

    /**
     * getter method for a task based on its index
     * @param index
     * @return
     */
    public Task getTask(int index) {
        return list.get(index);
    }

}
