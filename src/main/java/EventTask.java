public class EventTask extends Task {
    private String startTime;
    private String endTime;
    public EventTask(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        int from = startTime.indexOf(" ");
        String start = String.format("from: " + startTime.substring(from + 1));
        int to = endTime.indexOf(" ");
        String end = String.format("to: " + endTime.substring(to + 1));
        return String.format("[E]" + super.toString() + " (" + start + " " + end + ")");
    }
}
