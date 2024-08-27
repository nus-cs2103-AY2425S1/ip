public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String description, String from, String to) throws MurphyException{
        super(description);
        String fromTrimmed = from.trim();
        String toTrimmed = to.trim();
        if (fromTrimmed.isEmpty()) {
            throw new MurphyException("Event from date cannot be empty!");
        }
        if (toTrimmed.isEmpty()) {
            throw new MurphyException("Event to date cannot be empty!");
        }
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isDone, String from, String to) throws MurphyException{
        super(description, isDone);
        String fromTrimmed = from.trim();
        String toTrimmed = to.trim();
        if (fromTrimmed.isEmpty()) {
            throw new MurphyException("Event from date cannot be empty!");
        }
        if (toTrimmed.isEmpty()) {
            throw new MurphyException("Event to date cannot be empty!");
        }
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String toSaveString() {
        return "E|" + super.toSaveString() + "|" + this.from + "|" + this.to;
    }
}
