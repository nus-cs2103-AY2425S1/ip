import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void add(Task t) {
        this.tasks.add(t);
    }

    public void remove(int i) {
        this.tasks.remove(i);
    }

    public void sort() {

    }
    public Task get(int i) {
        return this.tasks.get(i);
    }
    public void mark(int i) {
        this.tasks.get(i).mark();
    }
    public void unmark(int i) {
        this.tasks.get(i).unmark();
    }
    @Override
    public String toString() {
        String temp = "";
        for (int i = 0; i < tasks.size(); i++){
            Task t = tasks.get(i);
            temp = temp.concat(String.format("%d-> [%s] %s\n", i + 1, t.getStatusIcon(), t));
        }
        return temp;
    }
}
