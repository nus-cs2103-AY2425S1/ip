package chatkaki.tasks;

public class Task {
    private String description;
    private boolean isDone = false;

    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void markAsDone() {
        if (this.isDone) {
            System.out.println("Task was already done!");
            return;
        }
        this.isDone = true;
        printTaskStatus("Nice! I've marked this task as done:");
    }

    public void markAsUndone() {
        if (!this.isDone) {
            System.out.println("Task was already undone!");
            return;
        }
        this.isDone = false;
        printTaskStatus("OK, I've marked this task as not done yet:");
    }

    private void printTaskStatus(String message) {
        System.out.println("\n____________________________________________________________");
        System.out.println(" " + message);
        System.out.println(" " + this);
        System.out.println("____________________________________________________________\n");
    }


    public String fileFormat() {
        return (this.isDone ? "true" : "false") + ","+ this.getDescription();
    }

    @Override
    public String toString() {
        return (this.isDone ? "[X] " : "[ ] ") + this.getDescription();
    }
}
