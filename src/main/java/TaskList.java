import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> listOfTasks;

    public TaskList() {
        listOfTasks = new ArrayList<Task>();
    }

    @Override
    public String toString() {
        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.println(i + ". " + listOfTasks.get(i).toString());
        }

        return "1";
    }
}
