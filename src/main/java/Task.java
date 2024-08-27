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

    public void markAsDone() {
        isDone = true;
        System.out.println("     _________________________________________________________");
        System.out.println("      Nice! I've marked this task as done:\n   " + "        " + this.toString());
        System.out.println("     _________________________________________________________");
    }

    public String getDescription() {
        return description;
    }

    public void unmark() {
        isDone = false;
        System.out.println("     _________________________________________________________");
        System.out.println("      I've marked this task as not done yet:\n   " + "        " + this.toString());
        System.out.println("     _________________________________________________________");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
