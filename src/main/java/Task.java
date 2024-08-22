public class Task {
    private static int totalTask;
    private boolean isDone;
    private String name;
    public Task(String name, boolean done) {
        this.isDone = done;
        this.name = name;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }
    public String getName() {
        return this.name;
    }

    public boolean getDone(){
        return this.isDone;
    }

    @Override
    public String toString() {
        String checked = this.isDone? "[X] ":"[ ] ";
        return (checked + this.name);
    }
}
