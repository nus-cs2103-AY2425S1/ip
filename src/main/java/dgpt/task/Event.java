package dgpt.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task of type "Event" in the Dgpt application.
 * <p>
 * A {@code Event} is a type of {@code Task} that has a specific start time and end time.
 * It is characterized by its description, which is inherited from the {@code Task} class.
 * </p>
 */
public class Event extends Task {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy, h:mma");

    /**
     * Constructs a {@code Event} task with the specified description, start time and end time.
     * By default, {@code isDone} is set to false.
     *
     * @param description The description of the {@code Event} task. This is a brief text that describes
     *                    what needs to be done.
     * @param startTime The start time of the {@code Event} task, in a dd/MM/yyyy HHmm format.
     * @param endTime The end time of the {@code Event} task, in a dd/MM/yyyy HHmm format.
     * @throws DateTimeParseException If the provided time and date is in an invalid format.
     */
    public Event(String description, String startTime, String endTime) throws DateTimeParseException {
        super(description);
        this.startTime = LocalDateTime.parse(startTime, inputFormatter);
        this.endTime = LocalDateTime.parse(endTime, inputFormatter);
    }

    public String getFromTimeInInputFormat() {
        return this.startTime.format(inputFormatter);
    }

    public String getToTimeInInputFormat() {
        return this.endTime.format(inputFormatter);
    }

    public String getFromTimeString() {
        return this.startTime.format(outputFormatter).replace("am", "AM")
                .replace("pm", "PM");
    }

    public String getToTimeString() {
        return this.endTime.format(outputFormatter).replace("am", "AM")
                .replace("pm", "PM");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.getFromTimeString() + " to: "
                + this.getToTimeString() + ")";
    }
}
