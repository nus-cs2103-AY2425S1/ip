package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class TaskList {

    Storage storage;
    ArrayList<Task> tasks;

    TaskList(ArrayList<Task> _tasks, Storage storage) {
        tasks = _tasks;
        this.storage = storage;
    }

    public Task remove(int index) throws IOException {

        Task task = tasks.remove(index);
        storage.writeData(this);
        return task;
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public boolean add(Task task) throws IOException {
        tasks.add(task);
        storage.writeData(this);
        return true;
    }

    public Task getLast(int index) {
        return tasks.get(tasks.size() - 1);
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public void sort(Comparator<? super Task> compare) throws IOException {
         tasks.sort(compare);
         storage.writeData(this);
    }
}
