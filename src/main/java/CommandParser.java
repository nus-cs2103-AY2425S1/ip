import java.util.HashMap;
import java.util.Map;

public class CommandParser {
  private String argument; // Input text after command title

  private String command;
  private String description;
  private Map<String, String> options = new HashMap<>();

  final private static String[] COMMANDS = {
      "list", "mark", "unmark", "delete", "todo", "deadline", "event", "find", "bye"
  };

  public CommandParser(String input) throws IllegalArgumentException {
    String[] parts = input.split(" ", 2);
    this.command = parts[0];

    if (!java.util.Arrays.asList(COMMANDS).contains(this.command)) {
      throw new IllegalArgumentException("Unknown command: " + this.command);
    }
    this.argument = parts.length > 1 ? parts[1] : "";
  }

  public void parse(boolean hasDescription) {
    this.parse(hasDescription, new CommandOption[0]);
  }

  public void parse(boolean hasDescription, CommandOption[] expectedOptions) {
    // Reset states
    this.description = "";
    options.clear();

    if (hasDescription && this.argument.length() == 0) {
      throw new IllegalCommandArgumentException(this.command, hasDescription, expectedOptions);
    }

    // Create a pattern to match all options and end of string
    StringBuilder endDelimeter = new StringBuilder("(?:$");
    for (CommandOption option : expectedOptions) {
      endDelimeter.append("| /");
      endDelimeter.append(option.getOption());
    }
    endDelimeter.append(")");
    String end = endDelimeter.toString();

    // Parse options
    // All options should start with /option_name <option_value>
    int minStartMatch = this.argument.length();
    for (CommandOption option : expectedOptions) {
      try {
        OptionMatch match = option.parse(this.argument, end);
        this.options.put(option.getOption(), match.getMatch());
        minStartMatch = Math.min(minStartMatch, match.getIndex());
      } catch (IllegalArgumentException e) {
        throw new IllegalCommandArgumentException(this.command, hasDescription, expectedOptions);
      }
    }

    // Get description
    String description = this.argument.substring(0, minStartMatch).trim();
    if (hasDescription && description.length() == 0) {
      throw new IllegalCommandArgumentException(this.command, hasDescription, expectedOptions);
    }
    this.description = description;
  }

  public String getCommand() {
    return this.command;
  }

  public String getDescription() {
    return this.description;
  }

  public String getOption(String option) {
    return this.options.get(option);
  }
}
