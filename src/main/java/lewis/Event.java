package lewis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Private constructor for a Task
     *
     * @param description A string description of the event
     */
   private Event(String description, Task.Status status, LocalDateTime from, LocalDateTime to) {
       super(description, status);
       this.from = from;
       this.to = to;
   }

    public static Event of(String description, LocalDateTime from, LocalDateTime to) {
        return new Event(description, Status.NOT_DONE, from, to);
    }
    /**
     * Factory method for instantiating an event while parsing from a csv file
     * This method should only be called when loading the event from Storage.
     * @param description A string representation of this event
     * @param status The event's completion status
     * @param from The starting date and time of this event
     * @param to The ending date and time of this event
     * @return an event
     */
   protected static Event of(String description, String status, String from, String to) {
       Task.Status newStatus = Task.Status.valueOf(status);
       LocalDateTime fromDateTime = LocalDateTime.parse(from);
       LocalDateTime toDateTime = LocalDateTime.parse(to);
       return new Event(description, newStatus, fromDateTime, toDateTime);
   }

    @Override
    public String toString() {
        String str = "[E]" +
                super.toString() +
                String.format("(From: %s To: %s)",
                        from.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")),
                        to.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")));
        return str;
    }

    /**
     * Returns a csv representation of this event.
     * @return A string in the form "Event,(task description),(task status),(from),(to)"
     */
    @Override
    protected String toCsv() {
        String csv = "Event," +
                super.toCsv() +
                "," +
                this.from +
                "," +
                this.to;
        return csv;
    }
}
