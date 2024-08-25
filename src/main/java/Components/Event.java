package Components;
import java.util.InputMismatchException;
public class Event extends Task {
    private String startTime;
    private String endTime;

    private Event(String description, String startTime, String endTime) {
        super(description); /* isolates the task description */
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Event createNewEvent(String textString) throws InputMismatchException {
        if (!textString.matches("^\\s+\\S.*\\s*/from\\s*\\S.*\\s*/to\\s*\\S.*")) {
            throw new InputMismatchException("Ensure that input contains description, start and end time");
        }

        String description = textString.replaceAll("/from.*", "").trim();
        String startTime = textString.replaceAll(".*/from\\s|\\s/to.*", "").trim();
        String endTime = textString.replaceAll(".*/to\\s", "").trim();
        return new Event(description, startTime, endTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
