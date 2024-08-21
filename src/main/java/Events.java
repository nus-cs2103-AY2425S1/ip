public class Events extends Task {

    String startTime;
    String endTime;
    public Events(String description, String start, String end) {
        super(description);
        this.startTime = start.replaceFirst("from ", "");
        this.endTime = end.replaceFirst("to ", "");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}
