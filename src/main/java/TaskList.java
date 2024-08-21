import java.util.Iterator;

public class TaskList implements Iterable<Task> {
    private Task[] tasks;
    private int maxSize;
    private int currSize;

    public TaskList(int maxSize) {
        this.maxSize = maxSize;
        tasks = new Task[maxSize];
        currSize = 0;
    }

    public int getIncompleteCount() {
        int count = 0;
        for (int i = 0; i < currSize; i++) {
            if (!tasks[i].isDone()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {
            private int curr = 0;

            @Override
            public boolean hasNext() {
                return curr < currSize;
            }

            @Override
            public Task next() {
                return tasks[curr++];
            }
        };
    }

    public void addTask(Task task) {
        // assumes we do not exceed max size
        tasks[currSize] = task;
        currSize++;
    }

    public Task getTask(int index) {
        // assumes valid index
        return tasks[index];
    }

    public void markComplete(int index) {
        // assumes valid index
        tasks[index].markAsDone();
    }

    public void markIncomplete(int index) {
        // assumes valid index
        tasks[index].markAsUndone();
    }
}
