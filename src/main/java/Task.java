import java.util.ArrayList;
public abstract class Task {

    private static final ArrayList<Task> list = new ArrayList<Task>();
    private final String task;
    public String type;
    private boolean completed;



    public Task(String t) {
        this.task = t;
        this.type = "[ ]";
        this.completed = false;
        Task.list.add(this);
    }

    public static void printList() {
        int a = 1;
        for (Task i : Task.list) {
            System.out.printf("%s.%s\n", a, i.toString());
            a++;
        }
    }

    public static void mark(int i) {
        Task t = Task.list.get(i);
        t.done();
    }

    public static void unmark(int i) {
        Task t = Task.list.get(i);
        t.undone();
    }

    public static void deleteTask(int i) {
        Task.list.remove(i-1);
    }

    private void done() {
        this.completed = true;
    }

    private void undone() {
        this.completed = false;
    }

    @Override
    public String toString() {
        String done;
        if (this.completed) {
            done = "[X]";
        } else {
            done = "[ ]";
        }
        return this.type + " " + done + " " + this.task;
    }
}