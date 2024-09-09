import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class eventTask extends Task{
    private Date starts, ends;
    private static final SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
    private static final SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy");

    eventTask(String status, String starts, String ends) throws ParseException {
        super(status, TaskType.EVENT);
        this.starts = inputFormat.parse(starts);
        this.ends = inputFormat.parse(ends);
    }

    String getStarts() {
        return outputFormat.format(starts);
    }

    String getEnds() {
        return outputFormat.format(ends);
    }

    @Override
    public String toString() {
        String icon = "[E]";
        return icon + super.toString() + " (from: " + outputFormat.format(starts) + "  to: " + outputFormat.format(ends) + ")";
    }
}
