package gray;

import gray.task.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList implements Serializable {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = new ArrayList<>(taskList);
    }

    public int size() {
        return taskList.size();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task get(int index) { return taskList.get(index); }

    public Task remove(int index) { return taskList.remove(index); }

    public List<Task> search(String search) {
        return taskList.stream()
                .filter(task -> task.toString().contains(search))
                .collect(Collectors.toList());
    }
}
