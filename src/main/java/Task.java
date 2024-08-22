public class Task {
    private String name;
    private boolean done = false;

    public Task(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public boolean isDone() {
        return this.done;
    }
    public void markDone() {
        this.done = true;
    }
    public void unmarkDone() {
        this.done = false;
    }
    public String getTaskInfo() {
        String check = this.isDone() ? "X" : " ";
        return String.format("[%s]. %s", check, this.getName());
    }
}
