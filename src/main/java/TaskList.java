import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    @Override
    public String toString() {
        int len = tasks.size();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < len; i++) {
            text.append(String.format("%d. %s \n", i + 1, tasks.get(i)));
        }
        return text.toString();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) throws AstraException {
        try {
            return tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new AstraException("Invalid index.");
        }
    }

    public int length() {
        return tasks.size();
    }
}
