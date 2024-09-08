package optimus;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> record;

    public TaskList() {
        this.record = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.record = tasks;
    }

    public void addTask(Task task) {
        record.add(task);
    }

    public void deleteTask(int index) {
        record.remove(index);
    }

    public Task getTask(int index) {
        return record.get(index);
    }

    public int sizeOfRecord() {
        return record.size();
    }

    public List<Task> getTasks() {
        return record;
    }

    public String findTasks(String keyword, Ui ui) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : record) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            return ui.printError("No matching tasks found. Try again");
        } else {
            return ui.listFoundTasks(matchingTasks);
        }
    }
}
