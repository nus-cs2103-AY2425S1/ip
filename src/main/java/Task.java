import java.util.ArrayList;
public class Task {

    private static final ArrayList<Task> list = new ArrayList<Task>();
    private final String task;


    public Task(String t) {
        this.task = t;
        Task.list.add(this);
    }

    public static void printList() {
        int a = 1;
        for (Task i : Task.list) {
            System.out.printf("%s.%s\n", a, i.toString());
            a++;
        }
    }


    @Override
    public String toString() {
        return " " + this.task;
    }
}