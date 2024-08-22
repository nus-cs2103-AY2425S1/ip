import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public ArrayList<Task> retrieveTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    public Task getTask(int number) {
        return tasks.get(number - 1);
    }

    public Task removeTask(int number) {
        return tasks.remove(number - 1);
    }

    public Task markTask(int number) {
        Task task = this.getTask(number);
        task.setCompletionStatus(true);
        return task;
    }

    public Task unmarkTask(int number) {
        Task task = this.getTask(number);
        task.setCompletionStatus(false);
        return task;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= this.getNumberOfTasks(); i++) {
            Task task = this.getTask(i);
            result.append(i).append(". ").append(task.toString()).append("\n");
        }
        return result.toString();
    }
}
