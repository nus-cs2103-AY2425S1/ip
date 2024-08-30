public class Event extends Task {
    String start;
    String end;
    public Event(String desc, String start, String end) {
        super(desc);
        int indexOfFrom = start.indexOf(" ") + 1;
        int indexOfEnd = end.indexOf(" ") + 1;
        this.start = start.substring(indexOfFrom).trim();
        this.end = end.substring(indexOfEnd).trim();
    }

    @Override
    public String toString() {
        return String.format("  [E][%s] %s (from: %s to: %s)",
                super.getStatusIcon(), super.toString(), this.start, this.end);
    }

    public String getFromTime() {
        return this.start;
    }

    public String getToTime() {
        return this.end;
    }
}
