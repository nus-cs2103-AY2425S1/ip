import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineBuilder extends TaskBuilder {

    @Override
    public Task build(String input) throws LukeException {
        int slashIndex = input.indexOf(" /by ");
        if (slashIndex == -1) {
            if (input.contains("/by")) {
                throw new LukeException("There needs to be spacing between /by and other words.");
            } else {
                throw new LukeException("Missing /by to indicate when the deadline of the task.");
            }
        }
        String description = input.substring(0, slashIndex).trim();
        String dateString = input.substring(slashIndex + 4).trim();
        try {
            return new DeadLine(description, DateTime.parseDate(dateString).format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
        } catch (DateTimeParseException e) {
            throw new LukeException("Invalid Date format");
        }
    }
}
