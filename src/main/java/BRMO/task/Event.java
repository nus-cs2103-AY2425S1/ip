package BRMO.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import BRMO.InvalidCommandException;

public class Event extends Task {
    private Date timeL;
    private Date timeR;
    private static final SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
    private static final SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy");

    public Event(String description, String timeL, String timeR) throws ParseException, InvalidCommandException {
        super(description);
        this.timeL = inputFormat.parse(timeL);
        this.timeR = inputFormat.parse(timeR);

        if (this.timeL.after(this.timeR)) {
            throw new InvalidCommandException("Invalid event time.");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + outputFormat.format(timeL) + " to: " + outputFormat.format(timeR) + ")";
    }

    @Override
    public String toText() {
        return "E | " + super.toText() + " | " + inputFormat.format(timeL) + " | " + inputFormat.format(timeR);
    }
}
