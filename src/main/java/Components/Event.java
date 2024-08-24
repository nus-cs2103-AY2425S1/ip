package Components;

public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event(String textString) {
        super(textString.replaceAll(" /.*", "")); /* isolates the task description */

        //TODO: add exception check to ensure that /from and /to are present
        
        this.startTime = textString.replaceAll(".*/from | /to.*", "");
        this.endTime = textString.replaceAll(".*/to ", "");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
