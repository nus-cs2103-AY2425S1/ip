import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        TaskList.list = list;
    }

    public static Task get(int index) {
        return TaskList.list.get(index);
    }

    public static int size() {
        return TaskList.list.size();
    }

    public static void add(Task task) {
        TaskList.list.add(task);
    }

    public static Task remove(int index) {
        return TaskList.list.remove(index);
    }
}
