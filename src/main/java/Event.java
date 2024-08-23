public class Event extends Task {
    private final String typeOfTask = "[E] ";
    private String startTime;
    private String endTime;

    public Event(String task, String startTime, String endTime) {
        super(task);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        String string = " (from: " + this.startTime + ", to: " + this.endTime + ")";
        if (taskIsDone()) {
            return typeOfTask + statusWhenDone() + taskString() + string;
        } else {
            return typeOfTask + statusWhenNotDone() + taskString() + string;
        }
    }
}
