import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;
    private static int numTasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

//    public TaskList(List<Task> tasks) {
//        this.tasks = tasks;
//    }

    boolean isEmpty() {
        return tasks.isEmpty();
    }

    List<Task> getTasks() {
        return tasks;
    }

    int getNumTasks() {
        return numTasks;
    }

    Task getTask(int taskNum) {
        return tasks.get(taskNum - 1);
    }

    void addTask(Task task) {
        tasks.add(task);
        numTasks++;
    }

    void delTask(int taskNum) throws BobException {
        try {
            tasks.remove(taskNum - 1);
            numTasks--;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new BobException("Invalid task number provided!");
        }
    }

    String printTasks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= numTasks; i++) {
            Task currTask = tasks.get(i - 1);
            if (i == numTasks) {
                sb.append(i).append(". ").append(currTask);
                continue;
            }
            sb.append(i).append(". ").append(currTask).append("\n");
        }
        return sb.toString();
    }
}

