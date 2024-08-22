public class Events extends Task{
    protected String start;
    protected String end;
    public Events(String description, String start, String end) {
        super(description + " ( from: " + start + ", to: " + end + ")", TaskType.EVENT);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getTaskType() {
        return "E";
    }
}
