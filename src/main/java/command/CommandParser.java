package command;

import java.util.regex.Pattern;

public class CommandParser {
    private final Pattern listPattern;
    private final Pattern markPattern;
    private final Pattern unmarkPattern;
    private final Pattern todoPattern;
    private final Pattern eventPattern;
    private final Pattern deadlinePattern;

    public CommandParser() {
        this.listPattern = Pattern.compile("^list$");
        this.markPattern = Pattern.compile("^mark \\d+$");
        this.unmarkPattern = Pattern.compile("^unmark \\d+$");
        this.todoPattern = Pattern.compile("^todo (\\w*\\s*)+");
        this.eventPattern = Pattern.compile("^event (\\w*\\s)+/from (\\w*\\s*)+ /to (\\w*\\s*)+");
        this.deadlinePattern = Pattern.compile("^deadline (\\w*\\s)*/by [\\w\\s]*");
    }
    public Command parse(String input) {
        if (input.isEmpty()) return new EmptyCommand();
        else if (listPattern.matcher(input).find()) return new ListCommand();
        else if (markPattern.matcher(input).find()) return new MarkCommand(input);
        else if (unmarkPattern.matcher(input).find()) return new UnmarkCommand(input);
        else if (todoPattern.matcher(input).find()) return new TodoCommand(input);
        else if (eventPattern.matcher(input).find()) return new EventCommand(input);
        else if (deadlinePattern.matcher(input).find()) return new DeadlineCommand(input);
        else return new UnknownCommand();
    }
}
