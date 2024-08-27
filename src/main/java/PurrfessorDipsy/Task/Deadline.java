package PurrfessorDipsy.Task;

import PurrfessorDipsy.Parser.DateTimeParser;

import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    protected String getTaskType() {
        return "Deadline";
    }

    @Override
    public LocalDate getRelevantDate() {
        return this.by;
    }

    @Override
    public String formatToCSV() {
        String res = super.formatToCSV();
        res += DELIMITER + wrapInQuotes(DateTimeParser.formatDateForStorage(by));
        return res;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeParser.formatDateForDisplay(by) + ")";
    }
}
