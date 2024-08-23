import java.util.ArrayList;

public class Tasks {
    private final ArrayList<Task> list;

    public Tasks() {
        list = new ArrayList<>();
    }

    public void add(Task task) {
        list.add(task);
    }

    public Task completeTask(int taskNumber) {
        if (taskNumber < 1 || taskNumber > list.size()) {
            throw new IllegalArgumentException(
                    String.format("Task does not exist. Number must be within the range 1 to %s.", list.size()));
        }
        Task t = list.get(taskNumber - 1);
        t.setComplete(true);
        return t;
    }

    public Task uncompleteTask(int taskNumber) {
        if (taskNumber < 1 || taskNumber > list.size()) {
            throw new IllegalArgumentException(
                    String.format("Task does not exist. Number must be within the range 1 to %s.", list.size()));
        }
        Task t = list.get(taskNumber - 1);
        t.setComplete(false);
        return t;
    }

    public int getSize() {
        return list.size();
    }

    @Override
    public String toString() {
        if (list.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            result.append(String.format("%d.%s%n", i + 1, list.get(i)));
        }

        // exclude the last newline character from getting printed
        return result.substring(0, result.length() - 2);
    }
}
