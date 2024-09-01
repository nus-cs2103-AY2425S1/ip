package duke.Tasks;
import duke.parsers.DateParser;

public class Event extends Task {
    private String from;
    private String to;

    DateParser dateParser = new DateParser();

    public Event(String s) {
        super(s.split(" /")[0].trim());
        String[] parts = s.split(" /");
        for (int i = 1; i < parts.length; i++) {
            String part = parts[i].trim();
            if (part.startsWith("from ")) {
                this.from = part.substring(5);
            } else if (part.startsWith("to ")) {
                this.to = part.substring(3);
            }
        }
        String toDate = dateParser.giveDate(to);
        if (toDate != null) {
            this.to = toDate;
        }
        String fromDate = dateParser.giveDate(from);
        if (fromDate != null) {
            this.from = fromDate;
        }

        if (parts[0].trim().isEmpty() || from == null || to == null) {
            throw new IllegalArgumentException("Duke.Tasks.Event must include both task description and 'from' and 'to' times and not include extra \"/\"");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}