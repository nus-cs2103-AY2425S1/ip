public class Event extends ToDo {
    private String startTime;
    private String endTime;

    public Event(String name, String startTime, String endTime) n{
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }
}