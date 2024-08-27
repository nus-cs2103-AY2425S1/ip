public abstract class Task implements java.io.Serializable {
    private static final long serialisableUid = 1L;
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

    public Task() {
        this("");
    }

    protected abstract Task mark();
    protected abstract Task unmark();
}
