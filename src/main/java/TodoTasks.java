public class TodoTasks extends Tasks{
    public TodoTasks(String name) {
        super(name);
    }

    @Override
    public String toString() {
        if (done) {
            return String.format("[T][X] %s", name);
        } else {
            return String.format("[T][] %s", name);
        }
    }
}
