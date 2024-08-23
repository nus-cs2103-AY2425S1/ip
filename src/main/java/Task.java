public class Task {
    private String description;
    private Task[] tasks;
    private int taskCount;

    public Task() {
        this.tasks = new Task[100];
        this.taskCount = 0;
    }
    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void addTask(Task task) {
        if(taskCount < tasks.length) {
            System.out.println("Got it, adding: " + task.getDescription());
            tasks[taskCount] = task;
            taskCount++;
        } else {
            System.out.println("Sorry, the task list is full.");
        }
    }

    public Task[] getTasks() {
        return tasks;
    }

    public int getTaskCount() {
        return taskCount;
    }
}