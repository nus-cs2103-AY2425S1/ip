import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class deadlineTask extends Task {
    private Date dl;
    private static final SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
    private static final SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy");

    deadlineTask(String status, String dl) throws ParseException {
        super(status, TaskType.DEADLINE);
        this.dl = inputFormat.parse(dl);
    }
   String getDeadline() {
        return outputFormat.format(dl);
    }

    @Override
    public String toString() {
        String icon = "[D]";
        return icon + super.toString() + " (by: " + outputFormat.format(dl) + ")";
    }
}
