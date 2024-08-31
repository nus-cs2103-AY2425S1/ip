package Tasks;

import java.util.InputMismatchException;
public class Event extends Task {
    private String startTime;
    private String endTime;

    private Event(String description, String startTime, String endTime) {
        super(description); /* isolates the task description */
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Event createNewEvent(String userInput) throws InputMismatchException {
        if (!userInput.matches("^\\s+\\S.*\\s*/from\\s*\\S.*\\s*/to\\s*\\S.*")) {
            throw new InputMismatchException("Ensure that input contains description, start and end time");
        }

        String description = userInput.replaceAll("/from.*", "").trim();
        String startTime = userInput.replaceAll(".*/from\\s|\\s/to.*", "").trim();
        String endTime = userInput.replaceAll(".*/to\\s", "").trim();
        return new Event(description, startTime, endTime);
    }
    public static Event createNewEvent(String description, String startTime, String endTime, boolean isMarked) {
        Event newEvent = new Event(description, startTime, endTime);
        if (isMarked) {
            newEvent.completeTask();
        }
        return newEvent;
    }

    @Override
    public String formatStringForSaving() {
        return toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
