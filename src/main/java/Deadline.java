import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Item {

    private LocalDate by;

    public Deadline(String newname, String by) {
        super(newname);
        // Parse the input string into a LocalDate object using the dd-MM-yyyy pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.by = LocalDate.parse(by, formatter);
        
    }

    @Override
    public String toData() {
        // Format the date to be stored as yyyy-MM-dd
        String str = String.format("D | %s | %s\n", super.toData(), this.by.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        return str;
    }

    @Override
    public String toString() {
        // Format the date as d MMMM yyyy (e.g., 1 June 2001)
        String formattedDate = this.by.format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
        String str = String.format("[D] %s (by: %s)", super.toString(), formattedDate);
        return str;
    }
}