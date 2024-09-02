package vecrosen;

import java.io.File;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this(new ArrayList<Task>());
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList(File file) {
        list = new ArrayList<Task>();
        Storage.load(file, list);
    }

    /**
     * Adds a task to the list of tasks.
     * @param t The task to be added
     */
    public void addTask(Task t) {
        list.add(t);
    }

    /**
     * Removes a task from the list of tasks.
     * @param index The index of the task to be removed
     * @return The description of the removed task
     */
    public String deleteTask(int index) {
        String rdesc = list.get(index).getDescription();
        list.remove(index);
        return rdesc;
    }

    /**
     * Marks a task as complete or incomplete.
     * @param index The index of the task to be modified
     * @param isDone The new state of the task
     * @return The description of the removed task
     */
    public String setDone(int index, boolean isDone) {
        Task t = list.get(index);
        t.setDone(isDone);
        return t.getDescription();
    }

    /**
     * Saves the contents of the list to the specified file
     * @param file
     */
    public void save(File file) {
        Storage.save(file, list);
    }

    /**
     * Prints out the contents of the list to the UI.
     * @param ui The UI object to print to.
     */
    public void printList(Ui ui) {
        ArrayList<Integer> indices = new ArrayList<Integer>();
        for (int i = 1; i <= list.size(); ++i) {
            indices.add(i);
        }
        ui.printList(list, indices, "Here are the tasks you have:", "You have no tasks!");
    }

    /**
     * Finds all tasks that contain the string given in the description. Case-sensitive.
     * @param s The keyword to search by
     * @param indices Array to hold the indices of the found tasks
     * @return The tasks matching the query
     */
    public ArrayList<Task> find(String s, ArrayList<Integer> indices) {
        ArrayList<Task> res = new ArrayList<Task>();
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).getDescription().contains(s)) {
                res.add(list.get(i));
                indices.add(i + 1);
            }
        }
        return res;
    }

    /**
     * Returns the number of tasks on the list.
     * @return Number of tasks on the list.
     */
    public int getListSize() {
        return list.size();
    }
}
