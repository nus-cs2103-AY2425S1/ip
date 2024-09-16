package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a specific due date and time.
 */
public class Deadline extends Task {
    private String type;
    private LocalDateTime dateTime;

    /**
     * Constructs a new Deadline object with specified description, type, date time and isDone.
     *
     * @param description String description of this Deadline object.
     * @param type Type of this Deadline object.
     * @param dateTime LocalDateTime (indicating deadline of the task) of this Deadline object.
     * @param isDone Boolean indicating the (Deadline) task is done or not.
     */
    public Deadline(String description, String type, LocalDateTime dateTime, boolean isDone) {
        super(description, isDone);
        this.type = type;
        this.dateTime = dateTime;
    }

    /**
     * Converts date time in this object to String using specified format.
     *
     * @param format String represents the desired format.
     * @return String representation of date time formatted according to the specified format.
     */
    private String convertDatetimeToString(String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        return this.dateTime.format(formatter);
    }

    /**
     * Converts this object to String representation (different format with toString()).
     *
     * @return String representation of this Deadline object.
     */
    @Override
    public String convertTaskToString() {
        return this.type + "::" + super.isDone() + "::" + super.getDescription() + "::"
                + convertDatetimeToString("yyyy-MM-dd HHmm") + "\n";
    }

    /**
     * Returns the type of this Deadline object.
     *
     * @return String representation of the type of this Deadline object.
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

        Deadline deadline = (Deadline) task;
        boolean isTypeSame = this.type.equals(deadline.type);
        boolean isDescriptionSame = super.getDescription().equals(deadline.getDescription());
        boolean isDateTimeSame = this.dateTime.equals(deadline.dateTime);

        return isTypeSame && isDescriptionSame && isDateTimeSame;
    }

    /**
     * Returns a String representation of this object.
     *
     * @return String representing this deadline object.
     */
    @Override
    public String toString() {
        return super.toString() + " (" + "by: " + convertDatetimeToString("dd MMM yyyy HH:mm") + ")";
    }

    /**
     * Compares two Deadline objects and determines if they are equal.
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

        Deadline deadline = (Deadline) o;
        boolean isStatusSame = super.isDone() == deadline.isDone();

        return isEqualWithoutStatus(deadline) && isStatusSame;
    }
}

