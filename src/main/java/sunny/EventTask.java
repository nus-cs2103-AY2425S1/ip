package sunny;

import java.time.LocalDate;

/**
 * Represent events
 */
public class EventTask extends Task {
    private String m1;
    private String timeline;
    private String m2;
    private String m3;
    private LocalDate startTime;
    private LocalDate endTime;
    /**
     * Initialises an event task object
     * @param description description of the event
     * @param isDone if the event is done
     */
    public EventTask(String description, boolean isDone) throws WrongMessageException {
        super(description, isDone);
        m1 = super.description.split("/from", 2)[0];
        timeline = super.description.split("/from", 2)[1];
        m2 = timeline.split("/to ", 2)[0];
        m3 = timeline.split("/to ", 2)[1];
        startTime = LocalDate.parse(m2.trim());
        endTime = LocalDate.parse(m3.trim());
        if (startTime.isBefore((endTime))) {
            throw new WrongMessageException();
        }
    }

    /**
     * Initialises event object and set isDone to false
     * @param description description of the event
     */
    public EventTask(String description) {
        super(description);
        m1 = super.description.split("/from", 2)[0];
        timeline = super.description.split("/from", 2)[1];
        m2 = timeline.split("/to ", 2)[0];
        m3 = timeline.split("/to ", 2)[1];
        startTime = LocalDate.parse(m2.trim());
        endTime = LocalDate.parse(m3.trim());

    }

    @Override
    public String getTimeline() {
        return timeline;
    }

    @Override
    public String toString() {
        if (isDone) {
            return String.format("[E][X] %s (from: %s to: %s)", m1, startTime.toString(), endTime.toString());
        } else {
            return String.format("[E][] %s (from: %s to: %s)", m1, startTime.toString(), endTime.toString());
        }
    }
}
