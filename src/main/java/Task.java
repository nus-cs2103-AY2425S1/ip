public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        System.out.println("     Nice! I've marked this task as done:");
        this.isDone = true;
        System.out.println("       " + this.toString());
    }
    public void unmark() {
        System.out.println("     OK, I've marked this task as not done yet:");
        this.isDone = false;
        System.out.println("       "+ this.toString());
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public abstract String getType();

    @Override
    public String toString() {
        return getType() + getStatusIcon() + " " + description;
    }

    public void printTaskAddedMessage(int taskCount) {
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + this);
        if (taskCount == 1) {
            System.out.println("     Now you have 1 task in the list.");
        } else {
            System.out.println("     Now you have " + taskCount + " tasks in the list.");
        }
    }

    public void printTaskRemovedMessage(int taskCount) {
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + this);
        if (taskCount == 1) {
            System.out.println("     Now you have 1 task in the list.");
        } else {
            System.out.println("     Now you have " + taskCount + " tasks in the list.");
        }
    }
}