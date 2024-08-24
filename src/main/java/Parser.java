import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
	public static void main(String[] args) {
		// Input string
		String input = "todo your mum";

		// Define the pattern to match commands, descriptions, and optional parameters
		Pattern pattern = Pattern.compile(
				"(\\w+)\\s+" +                // Command
				"([^/;]+)\\s*" +              // Description
				"(?:/by\\s+([^/.]*))?\\s*" +  // Deadline (optional)
				"(?:/from\\s+([^/;]*))?\\s*" +// From (optional)
				"(?:/to\\s+([^/;]*))?"        // To (optional)
		);

		Matcher matcher = pattern.matcher(input);

		// Find and print commands, descriptions, and parameters
		while (matcher.find()) {
			String command = matcher.group(1);           // Command
			String description = matcher.group(2).trim(); // Description
			String deadline = matcher.group(3);         // Deadline (optional)
			String from = matcher.group(4);             // From (optional)
			String to = matcher.group(5);               // To (optional)

			// Print results with descriptive variable names
			System.out.println("Command: " + command);
			System.out.println("Description: " + description);
			System.out.println("Deadline: " + (deadline != null ? deadline : "N/A"));
			System.out.println("From: " + (from != null ? from : "N/A"));
			System.out.println("To: " + (to != null ? to : "N/A"));
			System.out.println("---");
		}
	}
}
