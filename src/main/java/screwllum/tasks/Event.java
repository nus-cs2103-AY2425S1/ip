package screwllum.tasks;

import screwllum.utils.Parser;

import java.time.LocalDate;

public class Event extends Task {
    private LocalDate startDate;
    private LocalDate endDate;

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
     * Convert the task to a format suitable for saving to a file.
     *
     * @return A String in the format E_status_desc_startDate_endDate.
     */
    public String toSaveFormat() {
        return String.format("E_%s_%s_%s_%s", isDone ? "1" : "0", getDesc(),
                Parser.parseDateToString(startDate), Parser.parseDateToString(endDate));
    }
}
