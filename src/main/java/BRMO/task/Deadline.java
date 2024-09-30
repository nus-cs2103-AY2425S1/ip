package BRMO.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    private Date by;
    private static final SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
    private static final SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy");

    public Deadline(String description, String by) throws ParseException {
        super(description);
        this.by = inputFormat.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + outputFormat.format(by) + ")";
    }

    @Override
    public String toText() {
        return "D | " + super.toText() + " | " + inputFormat.format(by);
    }
}
