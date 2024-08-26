package blob;

public class Task {
    protected String type;
    protected String name;
    protected boolean isDone;

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public void complete() {
        this.isDone = true;
    }

    public void undo() {
        this.isDone = false;
    }

    public String check() {
        return isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return "[" + this.check() + "] " + this.name;
    }
}
