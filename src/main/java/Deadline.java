import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    //protected String by;
    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd/MM/yyyy]" + "[dd MMM yyyy]");
        LocalDate d = LocalDate.parse(by, formatter);
        this.date = d;
    }

    @Override
    public String toString() {
        String formatDate = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return "[D]" + super.toString() + " (by: " + formatDate + ")";
    }

    @Override
    public String formatData() {
        String formatDate = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return "D | " + super.formatData() + " | " + formatDate;
    }
}
