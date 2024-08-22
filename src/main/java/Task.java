public class Task {

    private final String taskName;

    private static int totalTasks = 0;
    private final int taskNo;

    public Task(String name) {
        taskName = name;
        taskNo = totalTasks + 1;
        totalTasks++;
    }

    @Override
    public String toString() {
        return String.format("%d. %s", taskNo, taskName);
    }
}
