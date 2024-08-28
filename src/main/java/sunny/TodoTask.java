package sunny;
/**
 * Represent todo tasks
 */
public class TodoTask extends Task {
    public TodoTask(String name) {
        super(name);
    }

    @Override
    public String toString() {
        if (isDone) {
            return String.format("[T][X] %s", name);
        } else {
            return String.format("[T][] %s", name);
        }
    }
}
