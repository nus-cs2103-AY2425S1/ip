package task;

/* System import */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String type;
    private LocalDateTime dateTime;

    /**
     * Constructs a new Deadline object with specified description, type, date time and isDone.
     *
     * @param desc String description of this Deadline object.
     * @param type Type of this Deadline object.
     * @param dateTime LocalDateTime (indicating deadline of the task) of this Deadline object.
     * @param isDone Boolean indicating the (Deadline) task is done or not.
     */
    public Deadline(String desc, String type, LocalDateTime dateTime, boolean isDone) {
        super(desc, isDone);
        this.type = type;
        this.dateTime = dateTime;
    }

    /**
     * Converts date time in this object to String using specified format.
     *
     * @param format String represents the desired format.
     * @return String representation of date time formatted according to the specified format.
     */
    private String datetimeToString(String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return this.dateTime.format(formatter);
    }

    /**
     * Converts this object to String representation (different format with toString()).
     *
     * @return String representation of this Deadline object.
     */
    @Override
    public String taskToString() {
        return this.type + "::" + super.getStatus() + "::" + super.getDesc() + "::" + datetimeToString("yyyy-MM-dd HHmm") + "\n";
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

    /**
     * Returns a String representation of this object.
     *
     * @return String representing this deadline object.
     */
    @Override
    public String toString() {
        return super.toString() + " (" + "by: " + datetimeToString("dd MMM yyyy HH:mm") + ")";
    }

    /**
     * Compares two Deadline objects and determines if they are equal.
     *
     * @param o Object to be compared.
     * @return True if both objects are of same reference or all attributes in both objects are the same, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Deadline t = (Deadline) o;
        return this.type.equals(t.type) && super.getDesc().equals(t.getDesc()) && this.dateTime.equals(t.dateTime) && (super.getStatus() == t.getStatus());
    }
}

