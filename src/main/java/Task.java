public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[]"); // mark done task with X
    }
    public String getDescription() {
        return description;
    }
    public void mark() {
        this.isDone = true;
        System.out.println("Marked "  + "\"" + description + "\"" + " as done");
        System.out.println(this.getStatusIcon() + " " + this.getDescription());
    }
    public void unmark() {
        this.isDone = false;
        System.out.println("Marked "  + "\"" + description + "\"" + " as not done");
        System.out.println(this.getStatusIcon() + " " + this.getDescription());
    }

    //...
}
