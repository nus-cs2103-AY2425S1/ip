import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Events extends Task {

    protected LocalDateTime eventStart;
    protected LocalDateTime eventEnd;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy[ ]HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Constructor for Events.
     * @param description the description of the event
     * @param eventStart the start date and time of the event
     * @param eventEnd the end date and time of the event
     * @throws ToothlessExceptions if the date and time format is invalid
     */
    public Events(String description, String eventStart, String eventEnd) throws ToothlessExceptions{
        super(description);
        try {
            this.eventStart = LocalDateTime.parse(eventStart.trim().replace("-","/"), INPUT_FORMATTER);
            this.eventEnd = LocalDateTime.parse(eventEnd.trim().replace("-","/"), INPUT_FORMATTER);
        } catch (Exception e) {
            throw new ToothlessExceptions("Please enter a valid date and time\n" +
                    "in the format: dd/MM/yyyy HHmm or dd-MM-yyyy HHmm\n" +
                    "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
        }
    }

    /**
     * Constructor for Events.
     * @param description the description of the event
     * @param eventStart the start date and time of the event
     * @param eventEnd the end date and time of the event
     * @param isDone the status of the event
     */
    public Events(String description, LocalDateTime eventStart, LocalDateTime eventEnd, boolean isDone) {
        super(description, isDone);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + eventStart.format(OUTPUT_FORMATTER) +" to: " + eventEnd.format(OUTPUT_FORMATTER) + ")";
    }
}
