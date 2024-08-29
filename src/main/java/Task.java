
public abstract class Task {
    private static int totalTask;
    private boolean isDone;
    private String name;
    public Task(String name, boolean done) {
        this.isDone = done;
        this.name = name;
    }
    public abstract String getType();
    public abstract String toFileString();
    public void mark() {
        this.isDone = true;
    }

    public boolean getIsDone(){
        return isDone;
    }
    public void unmark() {
        this.isDone = false;
    }
    public String getName() {
        return this.name;
    }

    
    @Override
    public String toString() {
        String checked = this.isDone? "[X] ":"[ ] ";
        return (checked + this.name);
    }
}
