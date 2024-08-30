package snowy;

import java.time.DateTimeException;
import java.time.LocalDate;


public class Deadline extends Task{
    private LocalDate date;

    public Deadline(String name, String date) throws SnowyException {
        super(name);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeException e) {
            throw new SnowyException("Wrong date format");
        }
    }

    @Override
    public String toString() {
        String temp = super.toString();
        return String.format("[D]%s (by %s)", temp, date.format(super.FORMATTER));
    }

    @Override
    public String toFileStorage() {
        String temp = super.toFileStorage();
        return String.format("D|%s|%s", temp, date.toString());
    }
}
