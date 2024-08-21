public class Task {
    private static int totalTask;
    private boolean done;
    private String name;
    public Task(String name, boolean done) {
        this.done = done;
        this.name = name;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }
    public String getName() {
        return this.name;
    }

    public boolean getDone(){
        return this.done;
    }

    @Override
    public String toString() {
        String checked = this.done? "[X] ":"[ ] ";
        return (checked + this.name);
    }
}
