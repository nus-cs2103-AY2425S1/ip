package data.task;

import java.time.LocalDate;
import java.util.ArrayList;

import data.datetime.BarneyDateTime;

public class EventTask extends Task {
    private String atString;
    private LocalDate atDate;
    private String toString;
    private LocalDate toDate;

    public EventTask(String description, String atString, String toString) {
        super(description);
        this.atString = atString;
        this.atDate = BarneyDateTime.parseDate(atString);
        this.toString = toString;
        this.toDate = BarneyDateTime.parseDate(toString);

    }

    @Override
    public ArrayList<String> toSaveArray() {
        ArrayList<String> rtr = super.toSaveArray();
        rtr.add("E");
        rtr.add(this.atString);
        rtr.add(this.toString);
        return rtr;
    }

    @Override
    public String toString() {
        String rtrAtString = (this.atDate != null) ? BarneyDateTime.formatDate(this.atDate) : this.atString;

        String rtrToString = (this.toDate != null) ? BarneyDateTime.formatDate(this.toDate) : this.toString;

        return "[E]" + super.toString() + " (at: " + rtrAtString + " to: " + rtrToString + ")";
    }
}
