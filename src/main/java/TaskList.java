import java.sql.Array;
import java.util.ArrayList;

public class TaskList {

    public static ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void printList() {
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            String listMessage = "  " + (i + 1) + "." + t;
            System.out.println(listMessage);
        }
    }

    public void mark(int taskNumber) {
        Task t = tasks.get(taskNumber - 1);
        t.markAsDone();
    }

    public void unmark(int taskNumber) {
        Task t = tasks.get(taskNumber - 1);
        t.markAsNotDone();
    }

    public void delete(int taskNumber) {
        if (tasks.isEmpty()) {
            return;
        }
        tasks.remove(taskNumber - 1);
    }
}
