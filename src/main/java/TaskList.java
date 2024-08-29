import java.util.ArrayList;

public class TaskList {
    // Contains the task list e.g., it has operations to add/delete tasks in the list
    ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(Task[] tasks) {
        for (Task t : tasks) {
            this.tasks.add(t);
        }
    }
}
