import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandOption {
  private final String option;
  private final String description;

  public CommandOption(String option, String description) {
    this.option = option;
    this.description = description;
  }

  public String getOption() {
    return this.option;
  }

  public String getDescription() {
    return this.description;
  }

  // Returns the index of the start of the match and the matched string
  // If the option is not found, an IllegalArgumentException is thrown
  public OptionMatch parse(String input, String endDelimeter) throws IllegalArgumentException {
    Pattern pattern = Pattern.compile("/" + this.option + " (.+?)" + endDelimeter);
    Matcher matcher = pattern.matcher(input);
    if (!matcher.find()) {
      throw new IllegalArgumentException();
    }
    return new OptionMatch(matcher.start(), matcher.group(1));
  }
}
