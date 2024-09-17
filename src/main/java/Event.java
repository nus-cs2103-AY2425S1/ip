import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDate from, LocalDate to) {
        /**
         * Instatiate event without needing to input time
         * Defaults from time to start of the day and end time to end of the day
         */
        super(description);
        this.from = from.atStartOfDay(); // Set default time to start of day
        this.to = to.atStartOfDay().plusDays(1).minusSeconds(1); // Set end of day time
    }

    public Event(String description, boolean isDone, LocalDate from, LocalDate to) {
        /**
         * Instatiate event without needing to input time && isDone
         * Defaults from time to start of the day and end time to end of the day
         */
        super(description, isDone);
        this.from = from.atStartOfDay(); // Set default time to start of day
        this.to = to.atStartOfDay().plusDays(1).minusSeconds(1); // Set end of day time
    }

    @Override
    public String toString() {
        DateTimeFormatter sameYearFullFormatter = DateTimeFormatter.ofPattern("d MMM HH:mm");
        DateTimeFormatter diffYearFullFormatter = DateTimeFormatter.ofPattern("d MMM yyy HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMM");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        //Different days, same year
        String fromString = this.from.format(sameYearFullFormatter);
        String toString = this.to.format(sameYearFullFormatter);

        //Different Year
        if (LocalDate.now().getYear() != this.from.getYear()) {
            fromString = this.from.format(diffYearFullFormatter);
            toString = this.to.format(diffYearFullFormatter);

        } else if (this.from.toLocalDate().equals(this.to.toLocalDate())) {
            //Within the same day and same year
            toString = this.to.format(timeFormatter);

        } else if (this.from.toLocalTime().equals(LocalDateTime.MIN.toLocalTime()) &&
                this.to.toLocalTime().equals(LocalDateTime.MIN.toLocalTime())) {
            //same year, not same day, but whole day
            fromString = this.from.format(dateFormatter);
            toString = this.to.format(dateFormatter);
        }

        return "[E] " + super.toString() + " | " + fromString + " - " + toString;
    }
}