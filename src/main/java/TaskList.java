import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listOfTasks;

    public TaskList() {
        this.listOfTasks = new ArrayList<Task>();
    }

    public void add(Task task) {
        this.listOfTasks.add(task);
    }

    public Task number(int index) {
        if (listOfTasks.isEmpty()) return null;
        return this.listOfTasks.get(index);
    }

    public void finish(int index) {
        listOfTasks.get(index).finish();
    }

    public void unfinish(int index) {
        listOfTasks.get(index).unfinish();
    }

    public int numOfTasks() {
        return this.listOfTasks.size();
    }

    @Override
    public String toString() {
        if (this.listOfTasks.size() <= 0) {
            return "Maybe you can try adding tasks :)";
        }
        String myTasks = "";
        for (int i = 0; i < this.listOfTasks.size(); i++) {
            myTasks += (i+1) + ". " + this.listOfTasks.get(i).toString() + "\n";
        }

        return myTasks;
    }
}
