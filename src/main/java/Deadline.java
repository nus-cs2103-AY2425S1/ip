import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private LocalDate by;
    private DateTimeFormatter outputFormatter;

    public Deadline(String description, String by) {
        super(description);

        //Define input date format
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Convert the string to a LocalDate
        this.by = LocalDate.parse(by, inputFormatter);

        DateTimeFormatter outputFormatter =  DateTimeFormatter.ofPattern("MMM-dd-yyyy");
        this.outputFormatter = outputFormatter;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getPrintedDeadline() + ")";
    }

    public String getPrintedDeadline() {
        return this.by.format(this.outputFormatter);
    }

    public String getStorageDeadline() {
        return this.by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String getSavedFormat() {
        return "D | " + (this.getIsDone() ? "1" : "0") + " | " + this.getDescription() + " | " + this.getStorageDeadline() +"\n";
    }
}