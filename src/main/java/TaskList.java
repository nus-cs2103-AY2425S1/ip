import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<Task>();

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public String howManyTasks() {
        return "Homie, you have " + taskList.size() + " task(s) in the list now.";
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(int taskNumber) {
        taskList.remove(taskNumber - 1);
    }

    public void markTaskAsDone(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.markAsDone();
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int taskNumber) {
        return taskList.get(taskNumber - 1);
    }

    public void markTaskAsUndone(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.markAsUndone();
    }
}
