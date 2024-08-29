import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public Task mark(int index) {
        Task t = list.get(index);
        t.isDone = true;
        return t;
    }

    public Task unmark(int index) {
        Task t = list.get(index);
        t.isDone = false;
        return t;
    }

    public void list() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
    }

    public Task delete(int index) {
        Task t = list.get(index);
        list.remove(index);
        return t;
    }

    public void add(Task t) {
        list.add(t);
    }

    public Task get(int index) {
        return list.get(index);
    }

    public int length() {
        return list.size();
    }


}
