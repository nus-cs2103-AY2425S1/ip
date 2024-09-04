import java.time.LocalDate;

class Event extends Task {
    LocalDate from;
    LocalDate to;

    Event(String task, String from, String to) {
        super(task);
        this.from = super.stringToDate(from);
        this.to = super.stringToDate(from);
    }

    Event(int status, String task, String from, String to) {
        super(status, task);
        this.from = super.stringToDate(from);
        this.to = super.stringToDate(from);
    }

    public String toString() {
        return "[E]" + super.toString() + 
            " (from: " + this.from.toString() + " to: " + this.to.toString() + ")";
    }

    String toSaveAsString() {
        return "E" + super.toSaveAsString() + "/" + this.from.toString() + "/" + this.to.toString();
    }
}