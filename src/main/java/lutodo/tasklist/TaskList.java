package lutodo.tasklist;

import java.util.ArrayList;


import lutodo.tasklist.TaskList;
import lutodo.parser.Parser;
import lutodo.tasks.Task;
import lutodo.tasks.EventTask;
import lutodo.tasks.DeadlineTask;
import lutodo.tasks.TodoTask;
import lutodo.storage.Storage;


public class TaskList {
    public static final Task EMPTY_TASK = new Task("default");

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

}
