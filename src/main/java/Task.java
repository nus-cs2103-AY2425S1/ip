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

    public static void mark_task(int index) {
        Task temp = task_list.get(index-1);
        temp.setStatus(true);
        r.mark_message(temp.getName());
    }

    public static void unmark_task(int index) {
        Task temp = task_list.get(index-1);
        temp.setStatus(false);
        r.unmark_message(temp.getName());

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
