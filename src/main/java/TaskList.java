import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listOfTasks;
    public TaskList() {
        listOfTasks = new ArrayList<>();
    }

    public void add(String task) {
        listOfTasks.add(new Task(task));
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int length = listOfTasks.size();
        for (int i = 0; i < length; i++) {
            str.append((i + 1) + ". ");
            str.append(listOfTasks.get(i).toString());
            if (i != length - 1) {
                str.append("\n");
            }
        }
        return str.toString();
    }
}
