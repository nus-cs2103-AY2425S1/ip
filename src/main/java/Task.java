import java.util.ArrayList;
import java.util.HashSet;

public class Task {
    private String name;
    private Boolean status;
    private static Response r;
    public static ArrayList<Task> task_list = new ArrayList<>();

    public Task(String name) {
        this.name = name;
        this.status = false;
        r = new Response();
    }

    public void add_task(Task temp) {
        task_list.add(temp);
        r.added_task_message(temp.getName());
    }

    public static void list_task() {
        r.list_task_message(task_list);
    }

    public String getName() {
        return name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
