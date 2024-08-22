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
        System.out.println("Sure! I'll add that in for you.");
        System.out.printf("You now have %s tasks in your list.\n\n", Task.list.size());
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