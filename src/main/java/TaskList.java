import java.util.ArrayList;
import java.util.PriorityQueue;

public class TaskList {
    private static final ArrayList<Task> listOfTasks = new ArrayList<>();

    private static final PriorityQueue<Task> listOfTasksWithDeadline = new PriorityQueue<>();

    public TaskList() {

    }

    public TaskList(String[] arr) {
        try {
            for (String data : arr) {
                listOfTasks.add(Todo.parseData(data));
            }
        } catch (FailToParseDataException e) {
            System.out.println(e.getMessage());
        }
    }

    public String[] toDataStringArr() {
        String[] out = new String[listOfTasks.size()];
        return listOfTasks.stream().map(Task::toData).toArray(String[]::new);
    }

    public int size() {
        return listOfTasks.size();
    }

    public Task get(int index) {
        return listOfTasks.get(index);
    }

    public void remove(int index) {
        listOfTasks.remove(index);
    }

    public void add(Task t) {
        listOfTasks.add(t);
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < listOfTasks.size(); i++) {
            str += String.valueOf(i + 1) + "." + listOfTasks.get(i) + "\n";
        }
        return str;
    }
}
