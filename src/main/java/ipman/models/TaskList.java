package ipman.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * List of <code>Task</code>s
 */
public class TaskList implements Iterable<Task> {
    private final List<Task> tasks = new ArrayList<>();

    /**
     * Converts a previously serialized <code>Task</code> back to a
     * <code>Task</code> object.
     *
     * @param serializedTask task that was converted to a <code>String</code> by
     *                       <code>Task.serialize</code>
     * @return task that was represented by the string
     * @throws SerializationException the task type was not recognized and so
     *                                cannot be deserialized.
     * @see Task
     */
    public static Task deserialize(String serializedTask) {
        return switch (serializedTask.charAt(0)) {
        case Event.TASK_TYPE -> Event.deserialize(serializedTask);
        case Deadline.TASK_TYPE -> Deadline.deserialize(serializedTask);
        case ToDo.TASK_TYPE -> ToDo.deserialize(serializedTask);
        default -> throw new SerializationException(String.format(
            "Unrecognized task type %c",
            serializedTask.charAt(0)
        ));
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
