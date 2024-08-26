import java.util.ArrayList;
public abstract class Task {

    private static final ArrayList<Task> list = new ArrayList<Task>();
    private final String task;
    public String type;
    private boolean completed;
    private static Task loader;



    public Task(String t) {
        this.task = t;
        this.type = "[ ]";
        this.completed = false;
        Task.list.add(this);
    }

    public static int getNumTask() {
        return Task.list.size();
    }
    public static void printList() {
        int a = 1;
        for (Task i : Task.list) {
            System.out.printf("     %s.%s\n", a, i.toString());
            a++;
        }
        System.out.println();
    }

    public static int taskNum() {
        return Task.list.size();
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

    public static String taskFileFormatGet(int i) {
        return Task.list.get(i).saveFileFormat();
    }

    private void done() {
        this.completed = true;
    }

    private void undone() {
        this.completed = false;
    }

    public abstract String saveFileFormat();

    public String getTask() {
        return this.task;
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