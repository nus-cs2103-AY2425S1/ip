import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> lst;

    TaskList() {
        this.lst = new ArrayList<Task>();
    }

    String add(Task task) {
        lst.add(task);
        return "added: " + task;
    }

    String view() {
        int i = 1;
        String s = "";
        for (Task task : lst) {
            s += i + ". " + task + "\n";
            i++;
        }
        return s;
    }
}
