import java.util.ArrayList;

public class Tasks {
    private final ArrayList<Task> list;

    public Tasks() {
        list = new ArrayList<>();
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public Task completeTask(int taskNumber) {
        Task t = list.get(taskNumber - 1);
        t.setComplete(true);
        return t;
    }

    public Task unCompleteTask(int taskNumber) {
        Task t = list.get(taskNumber - 1);
        t.setComplete(false);
        return t;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            result.append(String.format("%d.%s%n", i + 1, list.get(i)));
        }

        // exclude the last newline character from getting printed
        return result.substring(0, result.length() - 1);
    }
}
