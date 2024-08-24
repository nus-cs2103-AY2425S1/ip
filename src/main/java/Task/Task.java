package Task;

public class Task {

    private final String desc;
    private boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    String getType() {
        return "[ ]";
    }

    public Boolean getDone(){
        return this.isDone;
    }

    public String getDesc(){
        return this.desc;
    }

    @Override
    public String toString() {
        return getType() + (isDone ? "[X] " : "[ ] ") + desc;
    }
}
