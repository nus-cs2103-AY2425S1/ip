public class EventTask extends Task{
    
    private String startDate;
    private String endDate;

    EventTask(String taskName, String startDate, String endDate) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(From: " + this.startDate + " To: " + this.endDate + ")";
    }
}

