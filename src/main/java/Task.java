public abstract class Task {
    //instance fields
    protected final String description;
    protected boolean isComplete;

    public Task(String description){
        this.description = description;
        this.isComplete = false;
    }
    public Task(String description, boolean isComplete){
        this.description = description;
        this.isComplete = isComplete;
    }

    protected abstract Task mark();
    protected abstract Task unmark();
}
