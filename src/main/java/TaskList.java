import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int size() {
        return taskList.size();
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void markTask(int index, boolean isDone) {
        Task task = taskList.get(index);
        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }
    }
}
