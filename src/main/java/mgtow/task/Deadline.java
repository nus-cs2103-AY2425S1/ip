package mgtow.task;

import mgtow.util.InvalidTaskException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime end;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");

    public Deadline(String desc, String end) throws InvalidTaskException {
        super(desc, "D");
        try {
            this.end = LocalDateTime.parse(end, INPUT_FORMAT);
        } catch (Exception e) {
            throw new InvalidTaskException("Invalid date format. Use yyyy-MM-dd HHmm");
        }
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public String getDeadline() {
        return end.format(INPUT_FORMAT);
    }

    @Override
    public String toString(){
        return super.toString() + "(by: " + end.format(OUTPUT_FORMAT) + ")";
    }
    @Override
    public boolean isOnDate(LocalDate date) {
        return getEnd().toLocalDate().equals(date);
    }


}
