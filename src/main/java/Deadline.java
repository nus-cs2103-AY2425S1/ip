import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

public class Deadline extends Task{

    protected String by;
    protected LocalDateTime deadline;
    protected final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        if (by.contains("-")) {
            String[] parseDT = by.split("-");
            this.deadline = LocalDateTime.of(Integer.parseInt(parseDT[0]),
                    Integer.parseInt(parseDT[1]), Integer.parseInt(parseDT[2]), 23, 59);
        } else {
            this.deadline = LocalDateTime.parse(by, FORMAT);
        }
    }

    @Override
    public String write() {
        return "D" + super.write() + " | " + deadline.format(FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(FORMAT) + ")";
    }
}
