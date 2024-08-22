public class Events extends Task{
    public Events(String description, String start, String end) {
        super(description + " ( from: " + start + ", to: " + end + ")");
    }

    @Override
    public String getTaskType() {
        return "E";
    }
}
