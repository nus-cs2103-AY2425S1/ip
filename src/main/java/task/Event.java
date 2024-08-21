package task;

public class Event extends Task {
    private final String startDate;
    private final String endDate;

    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = "[E]";
        System.out.println("Successfully added task: " + this.toString());
        super.outputTaskCount();
    }

    @Override
    public String toString() {
        if (isCompleted) {
            return this.type + "[x] - " + this.description + "(" + this.startDate + "to" + this.endDate + ")";
        }
        return this.type + "[ ] - " + this.description + " (" + this.startDate + " to " + this.endDate + ")";
    }
}
