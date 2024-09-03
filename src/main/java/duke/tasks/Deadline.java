package duke.tasks;
import duke.additionalparsers.DateParser;


public class Deadline extends Task {
    private String by;
    DateParser dateParser = new DateParser();
    public Deadline(String s) {
        super(s.split(" /by ")[0]);
        String[] parts = s.split(" /by ", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new IllegalArgumentException("Duke.Tasks.Deadline must include both a task description and a 'by' time.");
        }
        this.by = parts[1].trim();
        String toDate = dateParser.giveDate(by);
        if (toDate != null) {
            this.by = toDate;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()  + " (by: " + by + ")";
    }
}