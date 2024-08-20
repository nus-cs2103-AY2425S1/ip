public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public void markAsDone() {
        if (this.isDone) System.out.println("Task already marked as done:\n" + this);
        else {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:\n" + this);
        }
    }

    public void markAsDoneNonVerbose() {
        if (!this.isDone) this.isDone = true;
    }

    public void markAsNotDone() {
        if (!this.isDone) System.out.println("Task already marked as not done:\n" + this);
        else {
            this.isDone = false;
            System.out.println("Ok, I've marked this task as not done yet:\n" + this);
        }
    }

    public String saveFormat() {
        return (this.isDone ? 1 : 0) + " | " + this.description;
    }
}
