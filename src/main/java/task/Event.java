package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a specific start date and time, and end date and time.
 */
public class Event extends Task {
    private String type;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    /**
     * Constructs a new Event object with specified description, type, start date time, end date time, and isDone.
     *
     * @param description String description of this Event object.
     * @param type Type of this Event object.
     * @param startDateTime LocalDateTime (indicating task start time) of this Event object.
     * @param endDateTime LocalDateTime (indicating task end time) of this Event object.
     * @param isDone Boolean indicating the (Event) task is done or not.
     */
    public Event(String description, String type,
                 LocalDateTime startDateTime, LocalDateTime endDateTime, boolean isDone) {
        super(description, isDone);
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Converts specified date time (LocalDateTime) to String using specified format.
     *
     * @param format String represents the desired format.
     * @param dt LocalDateTime to be converted.
     * @return String representation of date time formatted according to the specified format.
     */
    private String convertDatetimeToString(String format, LocalDateTime dt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        return dt.format(formatter);
    }

    /**
     * Converts this object to String representation (different format with toString()).
     *
     * @return String representation of this Event object.
     */
    @Override
    public String convertTaskToString() {
        return this.type + "::" + super.isDone() + "::" + super.getDescription() + "::"
                + convertDatetimeToString("yyyy-MM-dd HHmm", this.startDateTime) + "::"
                + convertDatetimeToString("yyyy-MM-dd HHmm", this.endDateTime) + "\n";
    }

    /**
     * Returns the type of this Event object.
     *
     * @return String representation of the type of this Event object.
     */
    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public boolean isEqualWithoutStatus(Task task) {
        if (this == task) {
            return true;
        }

        if (task == null || getClass() != task.getClass()) {
            return false;
        }

        Event event = (Event) task;
        boolean isTypeSame = this.type.equals(event.type);
        boolean isDescriptionSame = super.getDescription().equals(event.getDescription());
        boolean isStartDateTimeSame = this.startDateTime.equals(event.startDateTime);
        boolean isEndDateTimeSame = this.endDateTime.equals(event.endDateTime);

        return isTypeSame && isDescriptionSame && isStartDateTimeSame && isEndDateTimeSame;
    }

    /**
     * Returns a String representation of this object.
     *
     * @return String representing this Event object.
     */
    @Override
    public String toString() {
        return super.toString() + " ("
                + "from: " + convertDatetimeToString("dd MMM yyyy HH:mm", this.startDateTime)
                + " to: " + convertDatetimeToString("dd MMM yyyy HH:mm", this.endDateTime) + ")";
    }

    /**
     * Compares two Event objects and determines if they are equal.
     *
     * @param o Object to be compared.
     * @return True if both objects are of same reference or all attributes
     *     in both objects are the same, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Event event = (Event) o;
        boolean isStatusSame = super.isDone() == event.isDone();

        return isEqualWithoutStatus(event) && isStatusSame;
    }
}
