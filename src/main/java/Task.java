public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("  ");
        System.out.println(this);
    }

    public void unmarkDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.print("  ");
        System.out.println(this);
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        String output = "[" + getStatusIcon() + "] ";
        output += description;
        return output;
    }
}
