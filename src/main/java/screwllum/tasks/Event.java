package screwllum.tasks;

import java.time.LocalDate;

import screwllum.utils.Parser;

/**
 * Represents a task that occurs between two time periods
 */
public class Event extends Task {
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Constructs an object representing a task occurring between two time periods.
     *
     * @param desc  The description (name) of the task.
     * @param startDate The starting date of the task, which is required to be in a certain format.
     * @param endDate The ending date of the task, which is required to be in a certain format.
     */
    public Event(String desc, String startDate, String endDate) {
        super(desc);
        this.startDate = Parser.parseStringToDate(startDate);
        this.endDate = Parser.parseStringToDate(endDate);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                Parser.parseDateToString(startDate, "MMM dd yyyy"),
                Parser.parseDateToString(endDate, "MMM dd yyyy"));
    }

    /**
     * Converts the task to a format suitable for saving to a file.
     *
     * @return A String in the format E_status_desc_startDate_endDate.
     */
    public String toSaveFormat() {
        return String.format("E_%s_%s_%s_%s", isDone ? "1" : "0", getDesc(),
                Parser.parseDateToString(startDate), Parser.parseDateToString(endDate));
    }
}
