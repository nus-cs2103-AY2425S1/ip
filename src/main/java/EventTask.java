public class EventTask extends Task {
    String eventStart;
    String eventEnd;

    public EventTask(String task, String eventStart, String eventEnd) {
        super(task);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[E]");
        sb.append(super.toString());
        sb.append(" (from: " + this.eventStart + " to: " + this.eventEnd + ")");
        return sb.toString();
    }
}