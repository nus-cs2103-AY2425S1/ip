import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String name;
    protected boolean marked;

    public Task(String name) {
        this.name = name;
        this.marked = false;
    }

    public String getName() {
        return this.name;
    }

    public void setMark(boolean marked) {
        this.marked = marked;
    }

    @Override
    public String toString() {
        String marked = this.marked ? "[X]" : "[ ]";
        return marked + name;
    }
}

class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }
    @Override
    public String toString() {
        return "ToDo: " + super.toString();
    }
}
class Deadline extends Task {
    protected LocalDateTime by;
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }
    @Override
    public String toString() {
        String formattedBy = this.by.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        return "Deadline: " + super.toString() + " (by: " + formattedBy + ")";
    }
}
class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString() {
        String formattedFrom = this.from.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        String formattedTo = this.to.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        return "Event: " + super.toString() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }
}