public class EventTask extends Task {
    protected String date;
    protected String startTime;
    protected String endTime;

    public EventTask(String description, boolean isDone, String date, String startTime, String endTime) {
        super(description, isDone);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String printTask() {
        String output = "[E]";
        String status = (super.isDone ? "X" : " ");
        return output + "[" + status + "] " + super.description +
                " (" + this.date + " from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
