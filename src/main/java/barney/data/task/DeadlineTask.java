package data.task;

import java.time.LocalDate;
import java.util.ArrayList;

import data.datetime.BarneyDateTime;

public class DeadlineTask extends Task {
    private String byString;
    private LocalDate byDate;

    public DeadlineTask(String description, String byString) {
        super(description);
        this.byString = byString;
        this.byDate = BarneyDateTime.parseDate(byString);
    }

    @Override
    public ArrayList<String> toSaveArray() {
        ArrayList<String> rtr = super.toSaveArray();
        rtr.add("D");
        rtr.add(this.byString);
        return rtr;
    }

    @Override
    public String toString() {
        String rtrByString = (this.byDate != null) ? BarneyDateTime.formatDate(this.byDate) : this.byString;
        return "[D]" + super.toString() + " (by: " + rtrByString + ")";
    }
}
