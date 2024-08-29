import Tasks.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public String[] addTask(Task task) {
        this.tasks.add(task);
        return new String[] {"  ~  Cool! I added this task:",
                "  ~  " + task,
                "  ~  You now have " + + tasks.size() +
                        ((tasks.size() == 1) ? " task" : " tasks") + " in your list."};
    }
}
