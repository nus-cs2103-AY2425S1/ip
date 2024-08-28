package monique.task;

public abstract class Task implements java.io.Serializable {
    private static final long serialisableUid = 1L;
    //instance fields
    public final String description;
    public boolean isComplete;


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

    public abstract Task mark();
    public abstract Task unmark();
}
