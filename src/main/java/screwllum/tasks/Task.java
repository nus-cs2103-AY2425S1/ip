package screwllum.tasks;

public abstract class Task {
    protected String desc;
    protected boolean isDone;
    
    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public void toggleStatus() {
        isDone = isDone ? false : true;
    }

    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatus(), desc);
    }
    
    public abstract String toSaveFormat();
}
