package bestie.Parser;

import bestie.command.AddCommand;
import bestie.command.Command;
import bestie.command.ErrorCommand;
import bestie.task.Event;
import bestie.task.Priority;

import java.time.format.DateTimeParseException;

public class EventParser {

    public Command executeEventCommand(String userInput) {
        try {
            String[] partsOfEvent = userInput.split("/");
            String description = partsOfEvent[0].substring(6).trim();
            String start = partsOfEvent[1].substring(5).trim();
            String end = partsOfEvent[2].substring(3).trim();
            String priorityString = partsOfEvent[3].substring(8).trim().toUpperCase();
            Priority priority = Priority.valueOf(priorityString);
            return new AddCommand(new Event(description, start, end, priority));
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            String errorMessage = "You did not input the event in a valid format. " +
                    "Please follow the format \"event (name of event) /from (start time) "
                    + "/to (end time) /priority (high/medium/low)\" so that I can correctly add this event!\n" +
                    "Please stick to the correct format: YYYY-MM-DD HHMM for the start and end times.";
            return new ErrorCommand(errorMessage);

        } catch (DateTimeParseException e) {
            String errorMessage = "You did not input the date and time in the correct format." +
                    "Please stick to the correct format: YYYY-MM-DD HHMM";
            return new ErrorCommand(errorMessage);
        } catch (IllegalArgumentException e) {
            String errorMessage = "Invalid priority. Please specify as 'high', 'medium' or 'low'.";
            return new ErrorCommand(errorMessage);
        }
    }
}
