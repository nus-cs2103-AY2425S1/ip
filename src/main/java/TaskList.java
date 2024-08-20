public class TaskList {

    private String[] tasks;
    private int head;
    public TaskList() {
        // Assume no more than 100 tasks
        this.tasks = new String[100];
        this.head = 0;
    }

    public void add(String task) {
        this.tasks[this.head] = task;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int taskId = 0; taskId < this.head; taskId++) {
            sb.append(String.format("%d: %s", taskId + 1, tasks[taskId]));
        }
        return sb.toString();
    }
}
