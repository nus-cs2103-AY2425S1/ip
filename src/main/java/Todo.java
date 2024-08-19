public class Todo extends Task {
    private String name;
    private boolean isDone;
    public Todo(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
