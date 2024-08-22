public class Task {
    private boolean marked = false;
    private String name;
    public Task(String name) {
        this.name = name;
    }

    public void markCompleted() {
        this.marked = true;
    }

    public void markUncompleted() {
        this.marked = false;
    }

    @Override
    public String toString() {
        String status = this.marked ? "[X]" : "[ ]";
        return String.format("%s %s", status, this.name);
    }
}
