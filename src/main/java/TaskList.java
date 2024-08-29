import java.util.List;
import java.util.ArrayList;

class TaskList {
    private List<Task> taskList;

    TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    TaskList() {
        this.taskList = new ArrayList<>();
    }

    void add(Task task) {
        taskList.add(task);
    }

    Task get(int index) {
        return taskList.get(index);
    }

    List<Task> get() {
        return taskList;
    }

    Task delete(int index) {
        return taskList.remove(index);
    }

    int size() {
        return taskList.size();
    }
}
