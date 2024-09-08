package screwllum.tasks;

import screwllum.Parser;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String desc, String by) {
        super(desc);
        this.by = Parser.parseStringToDate(by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), Parser.parseDateToString(by, "MMM dd yyyy"));
    }

    public String toSaveFormat() {
        return String.format("D_%s_%s_%s", isDone ? "1" : "0", getDesc(), Parser.parseDateToString(by));
    }
}
