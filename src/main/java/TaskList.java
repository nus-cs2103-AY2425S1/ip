import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    private Storage storage;
    public TaskList(ArrayList<Task> taskList) {
        super(taskList);
    }

    public TaskList() {
        super();
    }
}
