package Task;

public class Task {

    private final String DESC;
    private boolean isDone;

    public Task(String desc) {
        this.DESC = desc;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    public String getType() {
        return "[ ]";
    }

    public Boolean getDone(){
        return this.isDone;
    }

    public String getDesc(){
        return this.DESC;
    }

    @Override
    public String toString() {
        return getType() + (isDone ? "[X] " : "[ ] ") + DESC;
    }
}
