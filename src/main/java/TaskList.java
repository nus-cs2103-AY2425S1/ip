import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> parent;
    private static int noOfTasks;

    public TaskList(int size) {
        this.parent = new ArrayList<Task>(size);
    }

    public void addTask(Task task) {
        this.parent.add(task);
        noOfTasks++;
        System.out.println("Friday > Successfully added: " + task.toString());
    }

    public void removeTask(int task) {
        Task temp = parent.get(task);
        this.parent.remove(task);
        noOfTasks--;
        System.out.println("Friday > Successfully removed: " + temp.toString());
    }

    @Override
    public String toString() {
        String ans = "";
        for (int i = 1; i <= noOfTasks; i++) {
            ans += String.format("%d: %s%n", i, parent.get(i-1).toString());
        }
        return ans;
    }

    public void doneTask(String action, int task) {
        if (action.equals("mark") || action.equals("Mark")) {
            this.parent.get(task).setDone();
            System.out.println("Friday > Good job! Marked as done :)");
        } else {
            this.parent.get(task).setUndone();
            System.out.println("Friday > Oh man! Marked as undone :(");
        }
    }
}
