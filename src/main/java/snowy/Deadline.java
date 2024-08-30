package snowy;

import java.time.LocalDate;


public class Deadline extends Task{
    private LocalDate date;

    public Deadline(String name, String date) {
        super(name);
        this.date = LocalDate.parse(date);
    }

    @Override
    public String toString() {
        String temp = super.toString();
        return String.format("[D]%s (by %s )", temp, date.format(super.FORMATTER));
    }

    @Override
    public String toFileStorage() {
        String temp = super.toFileStorage();
        return String.format("D|%s|%s", temp, date.toString());
    }
}
