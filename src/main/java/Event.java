public class Event extends Task {
    String startTime;
    String endTime;

    public Event(String task, String startTime, String endTime) {
        super(task);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String printTaskOnList() {
        if (marked) {
            return "[E][X] " + this.task + " (from: " + this.startTime + " to: " + this.endTime + ")";
        } else {
            return "[E][ ] " + this.task + " (from: " + this.startTime + " to: " + this.endTime + ")";
        }
    }

}
