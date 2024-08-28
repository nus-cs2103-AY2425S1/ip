public class Event extends Task {
    protected DateTime start;
    protected DateTime end;

    public Event(String desc, String start, String end) throws CloudException {
        super(desc);
        this.start = DateTime.of(start);
        this.end = DateTime.of(end);
    }

    @Override
    public String formatSave() {
        return "E | "
                + super.formatSave()
                + " | " + this.start.formatSave()
                + " | " + this.end.formatSave();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format(" (from: %s to: %s)", this.start, this.end);
    }
}
