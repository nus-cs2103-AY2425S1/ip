public class EventTask extends Task {
    private String eventStart;
    private String eventEnd;

    public EventTask(String task, String eventStart, String eventEnd) {
        super(task);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    @Override
    public String getStorageRepresentation() {
        if (this.isdone) {
            return "E|0|" + this.description + "|" + this.eventStart + "|" + this.eventEnd;
        } else {
            return "E|1|" + this.description + "|" + this.eventStart + "|" + this.eventEnd;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[E]");
        sb.append(super.toString());
        sb.append(" (from: " + this.eventStart + " to: " + this.eventEnd + ")");
        return sb.toString();
    }
}