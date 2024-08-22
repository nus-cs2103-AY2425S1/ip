package task;

/**
 * A task stored by BotManager
 *
 * @author dwsc37
 */
public class Task {
    private final String description;

    public Task(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
