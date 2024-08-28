/**
 * Represent events
 */
public class EventTasks extends Tasks{
    public EventTasks(String message) {
        super(message);
    }

    String m1 = super.name.split("/from", 2)[0];
    String timeline = super.name.split("/from", 2)[1];
    String m2 = timeline.split("/to ", 2)[0];
    String m3 = timeline.split("/to ", 2)[1];

    @Override
    public String getTimeline() {
        return timeline;
    }

    @Override
    public String toString() {
        if (isDone) {
            return String.format("[E][X] %s (from: %s to: %s)", m1, m2, m3);
        } else {
            return String.format("[E][] %s (from: %s to: %s)", m1, m2, m3);
        }
    }
}
