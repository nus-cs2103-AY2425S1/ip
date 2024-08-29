import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Events extends Task {
    LocalDate start;
    LocalDate end;
    Events(String taskString, String start, String end) {
        super(taskString);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    LocalDate getStart() {
        return this.start;
    }

    LocalDate getEnd() {
        return this.end;
    }

    @Override
    public String toFileString() {
        return "E | " + (done ? 1 : 0) +" | " + taskString + " | " + start + " | " + end;
    }

    @Override
    public String toString() {
        String convertedStart = start.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String convertedEnd = end.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + "(from: " + convertedStart + " to: " + convertedEnd + ")";
    }
}