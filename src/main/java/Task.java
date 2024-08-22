public class Task {
    protected boolean marked = false;
    protected String name;
    public Task(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String status = this.marked ? "[X]" : "[ ]";
        return status + " " + this.name;
    }
}
