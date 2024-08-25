public class Todo extends Task {
    private String description;
    private boolean isMarked;

    public Todo (String description) {
        this.description = description;
        this.isMarked = false;
    }

    @Override
    public void mark() {
        this.isMarked = true;
    }

    @Override
    public void unmark() {
        this.isMarked = false;
    }

    @Override
    public String toString() {
        return "[T] " + this.description;
    }
}
