import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>(100);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void deleteTask(int index) {
        this.taskList.remove(index);
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public int getTaskListSize() {
        return this.taskList.size();
    }

    public void enumerateList() {
        for (int i = 0; i < taskList.size(); i++) {
            String printFormat = String.format("%d %s", i + 1, this.getTask(i));
            System.out.println(printFormat);
        }
    }
}
