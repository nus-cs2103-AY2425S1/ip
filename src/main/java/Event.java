public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event(String task, String startTime, String endTime) {
        super("[E] ", task);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        String string = " (from: " + this.startTime + ", to: " + this.endTime + ")";
        return typeOfTaskString() + statusString() + taskString() + string;
    }
}
