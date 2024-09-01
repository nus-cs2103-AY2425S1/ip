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
        for (int i = 0; i < list.size(); ++i) {
            ui.speak((i+1) + "." + list.get(i).toString());
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
