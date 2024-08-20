public class TaskList {

    private Task[] tasks;
    private int head;
    public TaskList() {
        // Assume no more than 100 tasks
        this.tasks = new Task[100];
        this.head = 0;
    }

    public void add(String task) {
        this.tasks[this.head] = new Task(task);
        this.head++;
    }

    public void mark(int taskId) {
        tasks[taskId - 1].markDone();
    }

    public void unmark(int taskId) {
        tasks[taskId - 1].markNotDone();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int taskId = 0; taskId < this.head; taskId++) {
            sb.append(String.format("%d: %s\n", taskId + 1, tasks[taskId]));
        }
        return "Here are the tasks in your list:\n" + sb.toString();
    }
}
