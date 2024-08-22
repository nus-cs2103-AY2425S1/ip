public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    @Override
    public String toString() { 
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name; 
    } 
}
