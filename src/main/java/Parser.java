import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {


	public static final Pattern USER_INPUT_PATTERN = Pattern.compile(
			"(?<command>\\S+)" +                             // Command word
			"(?:\\s+(?<description>[^/]*))?" +                    // Description (optional)
			"(?:\\s*/by\\s+(?<by>[^/]*))?" +                      // Deadline (optional)
			"(?:\\s*/from\\s+(?<from>[^/]*))?" +                  // From (optional)
			"(?:\\s*/to\\s+(?<to>[^/]*))?"                        // To (optional)
	);


	public Parser() {

	}

	public static Command parse(String input) throws WigglyException {
		Matcher matcher = USER_INPUT_PATTERN.matcher(input);
		// Find and print commands, descriptions, and parameters
		if (matcher.find()) {

			try {
				String command = matcher.group("command").trim();
				String description = matcher.group("description");
				String by = matcher.group("by");
				String from = matcher.group("from");
				String to = matcher.group("to");

				switch (command) {
				case "list":
					return new ListCommand();
				case "todo":
					return new AddCommand(TaskType.TODO, description.trim(), null, null, null);
				case "deadline":
					return new AddCommand(TaskType.DEADLINE, description.trim(),
							LocalDate.parse(by.trim()), null, null);
				case "event":
					return new AddCommand(TaskType.EVENT, description.trim(), null,
							LocalDate.parse(from.trim()), LocalDate.parse(to.trim()));
				case "bye":
					return new ExitCommand();
				case "mark":
					return new MarkCommand(Integer.parseInt(description.trim()), true);
				case "unmark":
					return new MarkCommand(Integer.parseInt(description.trim()), false);
				case "delete":
					return new DeleteCommand(Integer.parseInt(description.trim()));
				default:
					return new InvalidCommand();
				}
			} catch (NullPointerException e) {
				throw new WigglyException("Failed to parse input: " + input);
			}
		} else {
			return new InvalidCommand();
		}
	}
}
