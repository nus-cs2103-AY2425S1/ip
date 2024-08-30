package socchat.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[]"); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
        System.out.println("Marked "  + "\"" + description + "\"" + " as done");
        System.out.println(this.toString());
    }

    public void unmark() {
        this.isDone = false;
        System.out.println("Marked "  + "\"" + description + "\"" + " as not done");
        System.out.println(this.toString());
    }

    public String getDescription() {
        return description;
    }

    public String getDoneStatus() {
        if (isDone) {
            return "Done";
        } else {
            return "Not done";
        }
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }

    public abstract String toSave();
    //...
}
