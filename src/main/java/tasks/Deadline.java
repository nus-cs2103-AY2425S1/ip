package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime deadline;
    private final DateTimeFormatter DateTimePrintOutputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
    private final DateTimeFormatter DateTimeFileOutputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = this.formatDate(deadline);
    }

    @Override
    public String toFileString() {
        return String.format("D %s | %s", super.toFileString(), this.deadline.format(DateTimeFileOutputFormatter));
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.deadline.format(DateTimePrintOutputFormatter));
    }

    private LocalDateTime formatDate(String dateTime) {
        String[] dateTimes = dateTime.split(" ");
        LocalDate date = LocalDate.parse(dateTimes[0]);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        LocalTime time = LocalTime.parse(dateTimes[1], timeFormatter);
        return LocalDateTime.of(date, time);
    }
}
