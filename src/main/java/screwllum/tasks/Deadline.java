package screwllum.tasks;

import screwllum.utils.Parser;

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

    /**
     * Convert the task to a format suitable for saving to a file.
     *
     * @return A String in the format D_status_desc_by.
     */
    public String toSaveFormat() {
        return String.format("D_%s_%s_%s", isDone ? "1" : "0", getDesc(), Parser.parseDateToString(by));
    }
}
