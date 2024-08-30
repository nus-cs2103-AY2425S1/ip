package sunny;

import java.time.LocalDate;

/**
 * Represent events
 */
public class EventTask extends Task {
    public EventTask(String description, boolean isDone) {

        super(description, isDone);
    }

    public EventTask(String description) {

        super(description);
    }

    String m1 = super.description.split("/from", 2)[0];
    String timeline = super.description.split("/from", 2)[1];
    String m2 = timeline.split("/to ", 2)[0];
    String m3 = timeline.split("/to ", 2)[1];
    LocalDate startTime = LocalDate.parse(m2.trim());
    LocalDate endTime = LocalDate.parse(m3.trim());


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
