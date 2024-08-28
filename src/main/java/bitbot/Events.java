package bitbot;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    protected LocalDateTime localDateTime, localDateTime1;
    protected LocalDate localDate, localDate1;
    protected LocalTime localTime, localTime1;

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    protected String from, to;

    public Events (String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    public Events (String description, LocalDateTime localDateTime, LocalDateTime localDateTime1) {
        super(description);
        this.localDateTime = localDateTime;
        this.localDateTime1 = localDateTime1;
    }

    public Events (String description, LocalDate localDate, LocalDate localDate1) {
        super(description);
        this.localDate = localDate;
        this.localDate1 = localDate1;
    }

    public Events (String description, LocalTime localTime, LocalTime localTime1) {
        super(description);
        this.localTime = localTime;
        this.localTime1 = localTime1;
    }


    @Override
    public String finalString() {
        if (localDateTime != null) {
            return "[E]" + super.finalString() + " (from: " + localDateTime.format(dateTimeFormatter) + " to: " + localDateTime1.format(dateTimeFormatter) + ")";
        } else if (localDate != null) {
            return "[E]" + super.finalString() + " (from: " + localDate.format(dateFormatter) + " to: " + localDate1.format(dateFormatter) + ")";
        } else if (localTime != null){
            return "[E]" + super.finalString() + " (from: " + localTime.format(timeFormatter) + " to: " + localTime1.format(timeFormatter) + ")";
        } else {
            return "[E]" + super.finalString() + " (from: " + from + " to: " + to + ")";
        }
    }

    @Override
    public String toFileFormat() {
        String fromToTimeString;

        if (localDateTime != null) {
            fromToTimeString = "E|" + (isDone ? "X" : " ") + "|" + taskDescription + "|" + localDateTime.format(dateTimeFormatter) + "|" + localDateTime1.format(dateTimeFormatter);
        } else if (localDate != null) {
            fromToTimeString = "E|" + (isDone ? "X" : " ") + "|" + taskDescription + "|" + localDate.format(dateFormatter) + "|" + localDate1.format(dateFormatter);
        } else if (localTime != null){
            fromToTimeString = "E|" + (isDone ? "X" : " ") + "|" + taskDescription + "|" + localTime.format(timeFormatter) + "|" + localTime1.format(timeFormatter);
        } else {
            fromToTimeString = "E|" + (isDone ? "X" : " ") + "|" + taskDescription + "|" + from + "|" + to;
        }
        return fromToTimeString;
    }
}