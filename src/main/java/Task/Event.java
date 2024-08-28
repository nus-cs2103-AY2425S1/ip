package task;

/* System import */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String type;
    private LocalDateTime startDateTime, endDateTime;

    public Event(String desc, String type, LocalDateTime startDateTime, LocalDateTime endDateTime, boolean isDone) {
        super(desc, isDone);
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    private String convertDatetimeToString(String format, LocalDateTime dt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return dt.format(formatter);
    }

    @Override
    public String convertTaskToString() {
        return this.type + "::" + super.isDone() + "::" + super.getDesc() + "::"
                + convertDatetimeToString("yyyy-MM-dd HHmm", this.startDateTime) + "::"
                + convertDatetimeToString("yyyy-MM-dd HHmm", this.endDateTime) + "\n";
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return super.toString() + " (" +
                "from: " + convertDatetimeToString("dd MMM yyyy HH:mm", this.startDateTime) +
                " to: " + convertDatetimeToString("dd MMM yyyy HH:mm", this.endDateTime) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Event t = (Event) o;
        return this.type.equals(t.type) && super.getDesc().equals(t.getDesc()) &&
                this.startDateTime.equals(t.startDateTime)&& this.endDateTime.equals(t.endDateTime) &&
                (super.isDone() == t.isDone());
    }
}
