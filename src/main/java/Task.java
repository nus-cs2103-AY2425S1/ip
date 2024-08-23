public class Task {
    private String description;
    private Task[] tasks;
    private int taskCount;
    private boolean isDone;

    public Task() {
        this.tasks = new Task[100];
        this.taskCount = 0;
    }
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return isDone ? "[X] " : "[ ] ";
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    public void addTask(Task task) {
        if(taskCount < tasks.length) {
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

    public void showNumberOfTasks() {
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    @Override
    public String toString() {
        return getStatus() + " " + getDescription();
    }
}