package bangmang.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter sameYearFullFormatter = DateTimeFormatter.ofPattern("d MMM HH:mm");
        DateTimeFormatter diffYearFullFormatter = DateTimeFormatter.ofPattern("d MMM yyy HH:mm");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        //Different days, same year
        String byString = this.by.format(sameYearFullFormatter);

        //Different Year
        if (LocalDate.now().getYear() != this.by.getYear()) {
            byString = this.by.format(diffYearFullFormatter);

        } else if (LocalDate.now().getDayOfYear() == (this.by.getDayOfYear())) {
            //Within the same day and same year
            byString = "today " + this.by.format(timeFormatter);
        }

        return "[D]" + super.toString() + " | " + byString;
    }
}