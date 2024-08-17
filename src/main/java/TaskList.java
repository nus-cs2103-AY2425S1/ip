public class TaskList {
    private final Task[] taskArray;
    public TaskList() {
        taskArray = new Task[100];
    }
    public void setTask(Task task, int index) {
        taskArray[index] = task;
    }
    public Task getTask(int index) {
        return taskArray[index];
    }
}
