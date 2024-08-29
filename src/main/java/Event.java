public class Event extends Task {
    String startTime;
    String endTime;

    public Event(String task, String startTime, String endTime, boolean isMarked) {
        super(task, isMarked);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String printTaskOnList() {
        if (isMarked) {
            return "[E][X] " + this.task + " (from: " + this.startTime + " to: " + this.endTime + ")";
        } else {
            return "[E][ ] " + this.task + " (from: " + this.startTime + " to: " + this.endTime + ")";
        }
    }

    @Override
    public String toFileFormat() {
        return "E | " + (this.isMarked ? "1" : "0") + " | " + this.task + " | " + this.startTime + " | " + this.endTime;
    }

}
