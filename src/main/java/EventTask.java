public class EventTask extends Task {
    private String start;
    private String end;
    public EventTask(String taskName, String start, String end) {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toFileFormat() {
        String divider = " | ";
        String status = this.getStatus() ? "1" : "0";
        return "E" + divider + status + divider + this.getTaskName() + divider + this.start + divider + this.end;
    }

    @Override
    public String toString() {
        if (this.getStatus()) {
            return "[E][X] " + this.getTaskName() + " (from: " + this.start + " to: " + this.end + ")";
        } else {
            return "[E][ ] " + this.getTaskName() + " (from: " + this.start + " to: " + this.end + ")";
        }
    }
}
