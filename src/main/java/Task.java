public class Task {
    private String description;
    private boolean isDone = false;

    public static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        taskCount++;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getTaskStatus() {
        return this.isDone;
    }

    public void MarkAsDone() {
        if (this.isDone) {
            System.out.println("Task was already done!");
            return;
        }
        this.isDone = true;
        System.out.println(this.getDescription());
        System.out.println("\n____________________________________________________________");
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + this.toString());
        System.out.println("____________________________________________________________\n");
    }

    public void MarkAsUndone() {
        if (!this.isDone) {
            System.out.println("Task was already undone!");
            return;
        }
        this.isDone = false;
        System.out.println("\n____________________________________________________________");
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + this.toString());
        System.out.println("____________________________________________________________\n");
    }

    public String toString() {
        return ((this.isDone ? "[X] " : "[ ] ") + this.getDescription());
    }
}
