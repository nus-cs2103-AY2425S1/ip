package Task;

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

    private String datetimeToString(String format, LocalDateTime dt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return dt.format(formatter);
    }

    @Override
    public String taskToString() {
        return this.type + "::" + super.getStatus() + "::" + super.getDesc() + "::" + datetimeToString("yyyy-MM-dd HHmm", this.startDateTime) + "::" + datetimeToString("yyyy-MM-dd HHmm", this.endDateTime) + "\n";
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + "from: " + datetimeToString("dd MMM yyyy HH:mm", this.startDateTime) + " to: " + datetimeToString("dd MMM yyyy HH:mm", this.endDateTime) + ")";
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
        return this.type.equals(t.type) && super.getDesc().equals(t.getDesc()) && this.startDateTime.equals(t.startDateTime) && this.endDateTime.equals(t.endDateTime) && (super.getStatus() == t.getStatus());
    }
}
