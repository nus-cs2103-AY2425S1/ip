// solution is adapted from the Course Level 3 extension

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String markDone() {
        this.isDone = true;
        return "Nice!  I've marked this task as done: \n" + this.printTask();
    }

    public String markUndone() {
        this.isDone = false;
        return "OK, I've marked this task as not done yet: \n" + this.printTask();
    }

    public String printTask() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }

    public static void main(String[] args) {
        Task task = new Task("Task 1");
        System.out.println(task.markDone());
        System.out.println(task.markUndone());
    }
}
