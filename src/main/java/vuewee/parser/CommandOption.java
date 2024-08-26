package vuewee.parser;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandOption<T> {
  private final String option;
  private final String description;
  private final Function<String, T> parser;
  private T parsedValue;

  public CommandOption(String option, String description, Function<String, T> parser) {
    this.option = option;
    this.description = description;
    this.parser = parser;
  }

  public static CommandOption<String> stringOption(String option, String description) {
    return new CommandOption<>(option, description, Function.identity());
  }

  public String getOption() {
    return this.option;
  }

  public String getDescription() {
    return this.description;
  }

  public T getParsedValue() {
    return this.parsedValue;
  }

  // Returns the index of the start of the match and saves the parsed value
  // If the option is not found, an IllegalArgumentException is thrown
  public int parse(String input, String endDelimeter) throws IllegalArgumentException {
    Pattern pattern = Pattern.compile("/" + this.option + " (.+?)" + endDelimeter);
    Matcher matcher = pattern.matcher(input);
    if (!matcher.find()) {
      throw new IllegalArgumentException();
    }
    this.parsedValue = this.parser.apply(matcher.group(1));
    return matcher.start();
  }
}
