public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void toggle() {
        this.isDone = !this.isDone;
    }

    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return ('[' + getStatus() + ']' + ' ' + name);
    }
}
