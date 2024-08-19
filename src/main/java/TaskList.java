import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listOfTasks;

    public TaskList() {
        this.listOfTasks = new ArrayList<Task>();
    }

    public void add(Task task) {
        this.listOfTasks.add(task);
    }

    @Override
    public String toString() {
        if (this.listOfTasks.size() <= 0) {
            return "Maybe you can try adding tasks :)";
        }
        String myTasks = "";
        for (int i = 0; i < this.listOfTasks.size(); i++) {
            myTasks += i + ". " + this.listOfTasks.get(i).toString() + "\n";
        }

        return myTasks;
    }
}
