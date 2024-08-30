import java.util.ArrayList;

/**
 * TaskList class contains the task list.
 */
public class TaskList {

    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void add(Task task) {
        this.list.add(task);
    }

    public void delete(int taskIndex) {
        this.list.remove(taskIndex);
    }

    public int getSize() {
        return this.list.size();
    }

    public Task getTask(int taskIndex) {
        return this.list.get(taskIndex);
    }

    public void markTask(int taskIndex) {
        this.getTask(taskIndex).mark();
    }

    public void unmarkTask(int taskIndex) {
        this.getTask(taskIndex).unmark();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }
}
