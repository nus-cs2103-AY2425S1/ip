package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class TaskList implements Iterable<Task> {
    private final List<Task> tasks = new ArrayList<>();

    public static Task deserialize(String line) {
        return switch (line.charAt(0)) {
            case Event.TASK_TYPE -> Event.deserialize(line);
            case Deadline.TASK_TYPE -> Deadline.deserialize(line);
            case ToDo.TASK_TYPE -> ToDo.deserialize(line);
            default -> throw new SerializationException(String.format("Unrecognized task type %c", line.charAt(0)));
        };
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public void remove(int index) {
        this.tasks.remove(index);
    }

    public int size() {
        return this.tasks.size();
    }

    @Override
    public void forEach(Consumer<? super Task> action) {
        this.tasks.forEach(action);
    }

    @Override
    public Spliterator<Task> spliterator() {
        return this.tasks.spliterator();
    }

    @Override
    public Iterator<Task> iterator() {
        return this.tasks.iterator();
    }
}
