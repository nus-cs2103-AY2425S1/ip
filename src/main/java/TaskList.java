import java.util.ArrayList;

public class TaskList {
    protected int curr = 0, end = 0, len = 0;
    private ArrayList<Task> tasks = new ArrayList<>(1028);
    protected void add(Task task) {
        tasks.add(task);
        end = (end + 1)%tasks.size();
        len++;
    }
    private Task poll() {
        Task task = tasks.get(curr);
        tasks.remove(curr);
        curr = (curr + 1)%tasks.size();
        len--;
        return task;
    }

    protected void update(int index, Task.Status status) {
        Task task = tasks.get((curr + index - 1) % tasks.size());
        task.updateStatus(status);
    }

    private Task peek() {
        return tasks.get(curr);
    }

    protected Task get(int i) {
        return tasks.get((curr + i - 1) % tasks.size());
    }

    protected void remove(int i) {
        tasks.remove((curr + i - 1)%tasks.size());
        len--;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < len; i++) {
            str.append(String.format("%d. ", i + curr + 1));
            str.append(tasks.get((curr + i)% tasks.size()).toString());
            str.append("\n");
        }
        return str.toString();
    }
}
