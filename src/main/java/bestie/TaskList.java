package bestie;// tasklist class should contain the task list, and have operations to add/delete tasks in the lsit

import java.util.ArrayList;
// mostly done?????????????????
public class TaskList {

    ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }
    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

}
