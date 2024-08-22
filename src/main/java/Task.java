/**
 * Class that encapsulates a task that can be added to `Torne`
 */
public class Task {
    private final String name;
    private boolean isComplete;

    Task(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
