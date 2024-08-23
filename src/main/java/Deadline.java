import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super("D", description);
        this.by = by;
    }

    @Override
    public String stringUI() {
        return super.stringUI() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + ")";
    }

    @Override
    public String stringStorage() {
        return super.stringStorage() + " | "
                + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

}
