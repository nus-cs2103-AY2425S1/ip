package screwllum.tasks;

import java.time.LocalDate;

import screwllum.utils.Parser;

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

    public String toSaveFormat() {
        return String.format("E_%s_%s_%s_%s", isDone ? "1" : "0", getDesc(),
                Parser.parseDateToString(startDate), Parser.parseDateToString(endDate));
    }
}
