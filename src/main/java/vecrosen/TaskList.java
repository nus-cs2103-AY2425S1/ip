package vecrosen;

import java.io.File;
import java.util.ArrayList;

/**
 * Handles manipulating the current session's list of tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Initializes an empty list of tasks.
     */
    public TaskList() {
        this(new ArrayList<Task>());
    }

    /**
     * Initializes the task list with the supplied list of tasks.
     * The same ArrayList will be used to store updates to the list and can be modified directly.
     * (How did you expect me to test the ArrayList if this thing deepcopied?)
     * @param list The ArrayList containing the initial list of tasks and to be used for storing the current state.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Initializes the
     * @param file
     */
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
     * @param index The index of the task to be removed, 1-indexed
     * @return The description of the removed task
     */
    public String deleteTask(int index) {
        index = index - 1;
        String rdesc = list.get(index).getDescription();
        list.remove(index);
        return rdesc;
    }

    /**
     * Marks a task as complete or incomplete.
     * @param index The index of the task to be modified, 1-indexed
     * @param isDone The new state of the task
     * @return The description of the removed task
     */
    public String setDone(int index, boolean isDone) {
        index = index - 1;
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
        for (int i = 0; i < list.size(); ++i) {
            ui.speak((i + 1) + "." + list.get(i).toString());
        }
        if (list.size() == 0) {
            ui.speak("You have no tasks!");
        }
    }

    /**
     * Returns the number of tasks on the list.
     * @return Number of tasks on the list.
     */
    public int getListSize() {
        return list.size();
    }
}
