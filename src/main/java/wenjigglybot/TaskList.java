package wenjigglybot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TaskList {

    static List<Task> tasks = new ArrayList<>(100);

    TaskList() {

    }

    TaskList(List<Task> tasks) {
        TaskList.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void remove(int i) {
        tasks.remove(i);
    }

    public List<Task> searchAndListTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }
}