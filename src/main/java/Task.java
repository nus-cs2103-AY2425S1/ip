public class Task implements Tasks {
    protected String task;
    protected boolean isDone;

    public Task(String task) {
        this.task = task;
    }

    @Override
    public void mark() {
        isDone = true;
        System.out.println("  " + this);
    }

    @Override
    public void unmark() {
        isDone = false;
        System.out.println("  " + this);
    }

    @Override
    public String toString() {
        String s = isDone ? "X" : " ";
        return "[" + s + "] " + task;
    }
}
