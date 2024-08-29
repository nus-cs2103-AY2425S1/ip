public class TaskList {
    private Task[] tasks;
    private int size;

    public TaskList() {
        this.tasks = new Task[100];
        this.size = 0;
    }

    public void addTask(Task task) {
        tasks[size] = task;
        size++;
    }

    public Task getTask(int index) {
        return tasks[index];
    }

    public int getSize() {
        return size;
    }

    public void deleteTask(int index) {
        for (int i = index; i < size - 1; i++) {
            tasks[i] = tasks[i + 1];
        }
        size--;
    }
}
