import java.util.ArrayList;
import java.util.HashSet;

public abstract class Task {
    private String name;
    private Boolean status;
    public static Response r;

    public String tag;
    public static ArrayList<Task> task_list = new ArrayList<>();

    public Task(String name, String tag) {
        this.name = name;
        this.status = false;
        this.tag = tag;
        r = new Response();
    }

    public static void delete_task(int index) {
        Task temp = task_list.get(index-1);
        r.delete_message(temp);
        task_list.remove(temp);
    }
//    public void add_task(Task temp) {
//        task_list.add(temp);
//        System.out.println("added: " + temp.getName());
//    }

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

    public String getTag() {
        return tag;
    }

    public int get_list_size(){
        return task_list.size();
    }

    public abstract String getDay();
    public abstract String getStart();
    public abstract String getEnd();

}
