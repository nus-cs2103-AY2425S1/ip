public class Task {
    private String desc;
    private String isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = "[ ]";
    }

    public void mark() {
        this.isDone = "[X]";
    }

    public void unmark() {
        this.isDone = "[ ]";
    }

    @Override
    public String toString() {
        return this.isDone + " " + this.desc;
    }
}
