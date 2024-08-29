import java.io.IOException;
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

    @Override
    protected void save() throws IOException {
        Storage.getWriter().write("D | " + (super.getIsDone() ? "1" : "0") + " | " + super.getDescription() 
            + " | " + this.getStorageDeadline() + "\n");
    }

    private String getPrintedDeadline() {
        return this.by.format(this.outputFormatter);
    }

    private String getStorageDeadline() {
        return this.by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}