package sunny;

import java.time.LocalDate;

/**
 * Represent events
 */
public class EventTask extends Task {
    /**
     * Initialises an event task object
     * @param description description of the event
     * @param isDone if the event is done
     */
    public EventTask(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Initialises a event object and set isDone to false
     * @param description
     */
    public EventTask(String description) {
        super(description);
    }

    private String m1 = super.description.split("/from", 2)[0];
    private String timeline = super.description.split("/from", 2)[1];
    private String m2 = timeline.split("/to ", 2)[0];
    private String m3 = timeline.split("/to ", 2)[1];
    private LocalDate startTime = LocalDate.parse(m2.trim());
    private LocalDate endTime = LocalDate.parse(m3.trim());


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
