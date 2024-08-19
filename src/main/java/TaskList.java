import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listOfTasks;

    public TaskList() {
        listOfTasks = new ArrayList<Task>();
    }

    public void add(String task) {
        listOfTasks.add(new Task(task));
    }

    @Override
    public String toString() {
        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.println(i + ". " + listOfTasks.get(i).toString());
        }

        return "1";
    }
}
