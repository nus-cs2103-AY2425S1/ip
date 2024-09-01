package meep.task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(TaskList taskList) {
        this.tasks = new ArrayList<>(taskList.tasks);
    }

    public void addItem(Task item) {
        this.tasks.add(item);
    }

    public void deleteItem(int index) {
        this.tasks.remove(index);
    }

    public void markAsDone(int index) {
        this.tasks.get(index).markAsDone();
    }

    public void markAsUndone(int index) {
        this.tasks.get(index).markAsUndone();
    }

    public String getTask(int index) {
        return this.tasks.get(index).toString() + "\n";
    }

    public String getLastTask() {
        return this.tasks.get(this.tasks.size() - 1).toString();
    }

    public String getList() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            list.append(i + 1).append(". ").append(this.tasks.get(i)).append("\n");
        }
        return list.toString();
    }

    public String getSaveFormatList() {
        StringBuilder list = new StringBuilder();
        for (Task task : this.tasks) {
            list.append(task.getSaveFormat()).append("\n");
        }
        return list.toString();
    }

    public String findTasks(String keyword) {
        TaskList foundTasks = new TaskList();
        for (Task task : this.tasks) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.addItem(task);
            }
        }
        return foundTasks.getList();
    }

    public int getSize() {
        return tasks.size();
    }
}
