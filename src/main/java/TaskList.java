import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }


    public void loadTask(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }


}
