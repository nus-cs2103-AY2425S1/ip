import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    private ArrayList<Task> allTasks = new ArrayList<>();

    /**
     * Returns all tasks in the task list of EchoBot.
     *
     */
    public void listAllTask() {
        String tasks = "";
        // Get task from task list
        for (int i = 0; i < this.allTasks.size(); i++) {
            tasks += (i + 1) + ". " + this.allTasks.get(i).toString();
            tasks += (i == this.allTasks.size() - 1) ? "" : "\n";
        }
        System.out.println(tasks);
    }

    public void markTask(int index) throws IndexOutOfBoundsException {
        this.allTasks.get(index).setMark();
    }

    public void unmarkTask(int index) throws IndexOutOfBoundsException {
        this.allTasks.get(index).setUnmark();
    }

    public void add(Task task) {
        this.allTasks.add(task);
        System.out.println(task);
        System.out.println("Now you have " + this.allTasks.size() +" tasks in the list.");
    }

    public void delete(int index) throws IndexOutOfBoundsException {
        System.out.println("Noted. I've removed this task:");
        System.out.println(allTasks.get(index));

        // Remove the task from list and return the size of list
        allTasks.remove(index);
        System.out.println("Now you have " + allTasks.size() +" tasks in the list.");
    }

}
