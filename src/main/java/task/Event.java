package task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Event extends Task {

    private LocalDate from;
    private LocalDate to;
    private LocalDateTime fromTime;
    private LocalDateTime toTime;
    
    public Event(String name, LocalDate from, LocalDate to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public Event(String name, LocalDate from, LocalDate to, boolean isDone) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.fromTime = from;
        this.toTime = to;
    }

    public Event(String name, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(name, isDone);
        this.fromTime = from;
        this.toTime = to;
    }

    public Event(String name, LocalDate from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.toTime = to;
    }

    public Event(String name, LocalDate from, LocalDateTime to, boolean isDone) {
        super(name, isDone);
        this.from = from;
        this.toTime = to;
    }

    public Event(String name, LocalDateTime from, LocalDate to) {
        super(name);
        this.fromTime = from;
        this.to = to;
    }

    public Event(String name, LocalDateTime from, LocalDate to, boolean isDone) {
        super(name, isDone);
        this.fromTime = from;
        this.to = to;
    }

    private String getDisplayStringFrom() {
        if (this.from != null) {
            return this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else {
            return this.fromTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        }
    }

    private String getDisplayStringTo() {
        if (this.to != null) {
            return this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else {
            return this.toTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        }
    }  

    private String getStringFrom() {
        if (this.from != null) {
            return this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {
            return this.fromTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
    }

    private String getStringTo() {
        if (this.to != null) {
            return this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {
            return this.toTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
    }

    @Override
    public String toFileString() {
        String done = this.isDone() ? "1" : "0";
        String stringFrom = this.getStringFrom();
        String stringTo = this.getStringTo();
        return "E," + done + "," + this.getName() + "," + stringFrom + "," + stringTo;
    }

    @Override
    public String toString() {
        String stringFrom = this.getDisplayStringFrom();
        String stringTo = this.getDisplayStringTo();
        return "[E] " + super.toString() + " (from: " + stringFrom + " to: " + stringTo + ")";
    }
}
