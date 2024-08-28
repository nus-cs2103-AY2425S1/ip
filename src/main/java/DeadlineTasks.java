import java.time.LocalDateTime;

/**
 * Represent tasks with deadlines
 */
public class DeadlineTasks extends Tasks{
    public DeadlineTasks(String message) {
        super(message);
    }
    String m1 = super.name.split("/by ", 2)[0];
    String deadline = super.name.split("/by ", 2)[1];
    LocalDateTime d = LocalDateTime.parse(deadline);


    @Override
    public String getTimeline() {
        return deadline;
    }
    @Override
    public String toString() {
        if (isDone) {
            return String.format("[D][X] %s (by: %s)", m1, deadline);
        } else {
            return String.format("[D][] %s (by: %s)", m1, deadline);
        }
    }
}
