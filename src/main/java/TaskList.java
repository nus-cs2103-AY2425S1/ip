import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskArrayList = new ArrayList<>();

    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }
    public void addTask(Task task) {
        taskArrayList.add(task);
    }
    public void delete(int index) {
        taskArrayList.remove(index - 1);
    }
    public void markDone(int index) {
        taskArrayList.get(index - 1).markDone();
    }
    public void unmarkDone(int index) {
        taskArrayList.get(index - 1).unmarkDone();
    }
    public String printTasks() {
        int count = 1;
        StringBuilder list = new StringBuilder();
        for(Task task: this.taskArrayList) {
            list.append(count++).append(": ").append(task.toString()).append("\n");
        }
        return list.toString();
    }
    public ArrayList<Task> getTaskArrayList() {
        return this.taskArrayList;
    }

    public int getSize() {
        return this.taskArrayList.size();
    }

    public Task getTask(int index) {
        return this.taskArrayList.get(index - 1);
    }
}
