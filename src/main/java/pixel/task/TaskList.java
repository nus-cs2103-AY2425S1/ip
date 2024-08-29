package pixel.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public TaskList(TaskList taskList) {
        this.list = new ArrayList<Task>();
        for (int i = 0; i < taskList.list.size(); i++) {
            Task originalTask = taskList.list.get(i);
            this.list.add(originalTask);
        }
    }

    public Task getTaskAtIndex(int index) {
        return list.get(index);
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public void deleteTask(Task task) {
        list.remove(task);
    }

    public int size() {
        return list.size();
    }
}
