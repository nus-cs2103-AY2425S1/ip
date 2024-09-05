public class Event extends Task {
    private String from;
    private String to;

    public Event (String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event (String description, String from, String to, boolean done) {
        super(description);
        this.from = from;
        this.to = to;
        if (done) {
            this.markTask();
        }
    }

    public Event (String str) {
        if (!str.contains("/from ") || !str.contains("/to ")) {
            try {
                throw new IncompleteEventOrDeadlineException();
            } catch (IncompleteEventOrDeadlineException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                String desc = str.substring(0, str.toLowerCase().indexOf("/from "));
                description = desc;
                from = parseDate(str.substring(str.toLowerCase().indexOf("/from ") + 6, str.toLowerCase().indexOf("/to ")));
                to = parseDate(str.substring(str.toLowerCase().indexOf("/to ") + 4, str.length()));
                if (from.isEmpty() || to.isEmpty() || desc.isEmpty()) {
                    throw new IncompleteEventOrDeadlineException();
                }
            } catch (IncompleteEventOrDeadlineException e) {
                System.out.println("Stop slacking off"); //TODO PUT PROPER WORD HERE
            }
        }
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + from + "to: " + to + ")";
    }

    @Override
    public String toUString() {
        String s = super.toUString();
        s += "002"; // Unique identifier for Event Tasktype
        s += super.description;
        s += "/from/";
        s += from;
        s += "/to/";
        s += to;
        return s;
    }
}
