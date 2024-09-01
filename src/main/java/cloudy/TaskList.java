package cloudy;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        }
        return null;
    }
    public Task removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.remove(index);
        }
        return null;
    }

    public ArrayList<Task> findTasks(String searchQuery) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.contains(searchQuery)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public int size() {
        return tasks.size();
    }

}
