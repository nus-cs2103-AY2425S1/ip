public class Task {
    private String task;
    protected boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
        EchoBot.dashline();
        System.out.println("added: " + this.task);
        EchoBot.dashline();
    }

    public void mark() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
    }

    public void unmark() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.toString());
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    @Override
    public String toString() {
        String statusIcon = this.getStatusIcon();
        return statusIcon + " " + this.task;
    }
}
