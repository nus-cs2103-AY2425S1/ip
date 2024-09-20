package nimbus.ui;

import java.util.ArrayList;

import nimbus.task.Task;


/**
 * This class store the arraylist of tasks
 */
public class TaskList {
    private static String horizontalLine = "\n-------------------------------------------------";
    private ArrayList<Task> taskList;
    /**
     * Creates a TaskList object with a new arraylist of size 100
     */
    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }

    /**
     * gets the arraylist of tasks
     *
     * @return arraylist of tasks
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * adds task into arraylist of tasks
     *
     * @param task task that is supposed to be added
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * deletes task from arraylist of tasks
     *
     * @param task task that is supposed to be deleted
     */
    public void delete(Task task) {
        this.taskList.remove(task);
    }

    @Override
    public String toString() {
        String output = "Nimbus says this is your list: \n";
        for (int i = 0; i < taskList.size(); i++) {
            if (i == taskList.size() - 1) {
                output += (String.valueOf(i + 1) + ". " + taskList.get(i));
            } else {
                output += (String.valueOf(i + 1) + ". " + taskList.get(i) + "\n");
            }
        }
        output += horizontalLine;
        System.out.println(output);
        return output;
    }
}
