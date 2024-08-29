import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate from;
    private LocalDate to;
    private DateTimeFormatter outputFormatter;

    public Event(String description, String from, String to) {
        super(description);

        // Define input date format
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Convert string to  LocalDate objects
        this.from = LocalDate.parse(from, inputFormatter);
        this.to = LocalDate.parse(to, inputFormatter);

        // Define output date format
        DateTimeFormatter outputFormatter =  DateTimeFormatter.ofPattern("MMM-dd-yyyy");
        this.outputFormatter = outputFormatter;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.getPrintedFrom() + " to: " + this.getPrintedTo() + ")";
    }

    @Override
    protected void save() throws IOException {
        Storage.getWriter().write("E | " + (super.getIsDone() ? "1" : "0") + " | " + super.getDescription() + " | " 
            + this.getStorageFrom() + " | " + this.getStorageTo() + "\n");
    }
    
    private String getPrintedFrom() {
        return this.from.format(this.outputFormatter);
    }

    private String getPrintedTo() {
        return this.to.format(this.outputFormatter);
    }

    private String getStorageFrom() {
        return this.from.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private String getStorageTo() {
        return this.to.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
