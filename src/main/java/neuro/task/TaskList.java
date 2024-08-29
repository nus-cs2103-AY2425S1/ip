package neuro.task;

import java.util.ArrayList;
import java.util.Iterator;

public class TaskList implements Iterable<Task> {
    ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public int getSize() {
        return taskList.size();
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public Task removeTask(int index) {
        return taskList.remove(index);
    }

    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();
    }
}