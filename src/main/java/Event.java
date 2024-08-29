public class Event extends Task {

    private final String start;
    private final String end;

    public Event(String taskInfo, String start, String end) {
        super(taskInfo);
        this.start = start;
        this.end = end;
    }

    @Override
    public String stringIsDone() {
        if (checkIsDone()) {
            return String.format("[E][X] %s (from: %s to: %s)", super.getTask(), this.start, this.end);
        } else {
            return String.format("[E][ ] %s (from: %s to: %s)", super.getTask(), this.start, this.end);
        }
    }

}
