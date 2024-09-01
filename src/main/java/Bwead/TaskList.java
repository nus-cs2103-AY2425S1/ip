package Bwead;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a location in a 2D space. A <code>Point</code> object corresponds to
 * a coordinate represented by two integers e.g., <code>3,6</code>
 */
public class TaskList {

    private static ArrayList<Task> currentList;

    /*
    public TaskList() {
        currentList = new ArrayList<>();
    }*/

    /**
     * Constructor for TaskList.
     *
     * @param listLoaded ArrayList with all the tasks (if any) from the local file.
     */
    public TaskList(ArrayList<Task> listLoaded) {
        currentList = listLoaded;
    }

    /**
     * Returns the current list of tasks.
     *=
     * @return ArrayList of tasks
     */
    public ArrayList<Task> getCurrentList() {
        return currentList;
    }

    /**
     * Prints current list of task.
     */
    public static void printlist() {
        for (int i = 1; i <= currentList.size(); i++) {
            Task task = currentList.get(i-1);
            System.out.println(i + "." + task.toString());
        }
    }

}
