package yappingbot.tasks;

import yappingbot.exceptions.YappingBotOOBException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class TaskList implements Iterable<Task> {
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

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    @Override
    public void forEach(Consumer<? super Task> action) {
        tasks.forEach(action);
    }

    @Override
    public Spliterator<Task> spliterator() {
        return tasks.spliterator();
    }
}
