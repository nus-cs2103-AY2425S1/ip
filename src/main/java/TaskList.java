import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> taskList = new ArrayList<>();

    public TaskList() {

    }

    public void addTask(String taskName) {
        taskList.add(new Task(taskName));
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();

        for (Task task : taskList) {
            str.append(task.toString()).append('\n');
        }

        if (!str.isEmpty()) {
            str.setLength(str.length() - 1);
        }
        return str.toString();

    }
}
