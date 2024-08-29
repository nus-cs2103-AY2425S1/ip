package tasks;

import exceptions.YappingBotInvalidTaskNumberException;
import exceptions.YappingBotOOBException;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;
    private int size;
    public TaskList() {
        tasks = new ArrayList<>();
    }
    public void add(Task task) {
        tasks.add(task);
        size += 1;
    }
    public Task delete(int index) throws YappingBotOOBException {
        Task t = get(index);
        tasks.remove(index);
        size -= 1;
        return t;
    }
    public int size() {
        return size;
    }
    public Task get(int index) throws YappingBotOOBException {
        if (index < 0 || index >= size) {
            throw new YappingBotOOBException(index);
        }
        return tasks.get(index);
    }
    public boolean isEmpty() { return size == 0; }
}
