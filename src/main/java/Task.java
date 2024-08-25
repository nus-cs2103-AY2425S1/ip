public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void markAsDone() {
        if (isDone) {
            System.out.println("Already done!");
        } else {
            isDone = true;
            String marked = "____________________________________________________________\n" +
                    "Nice! I've marked this task as done:\n" + this + "\n" +
                    "____________________________________________________________\n";
            System.out.println(marked);
        }
    }

    public void unmark() {
        if (!isDone) {
            System.out.println("Already unmarked!");
        } else {
            isDone = false;
            String unmarked = "____________________________________________________________\n" +
                    "OK, I've marked this task as not done yet:\n" + this + "\n" +
                    "____________________________________________________________\n";
            System.out.println(unmarked);
        }
    }

    @Override
    public String toString() {
       return getStatusIcon() + " " + description;
    }

    public String toSaveString() {
        return getStatusIcon() + " | " + description;
    }

}

