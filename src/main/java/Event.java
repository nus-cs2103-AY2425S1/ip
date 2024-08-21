public class Event extends Task {
    protected String startTime;
    protected String endTime;

    public Event(String description, String time) {
        super(description);
        String[] temp = time.split(" /to ");
        this.startTime = temp[0];
        this.endTime = temp[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }

}
