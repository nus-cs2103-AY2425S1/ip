package topaz.main;

import topaz.command.CreateCommand;
import topaz.command.DeleteCommand;
import topaz.command.Command;
import topaz.command.MarkCommand;
import topaz.command.TextCommand;
import topaz.exception.InvalidCommandException;

public class Parser {
    public static Command parse(String prompt) throws InvalidCommandException {

        if (prompt.equalsIgnoreCase("help")) {
            return new TextCommand("help");
        } else if (prompt.contains("bye")) {
            return new TextCommand("bye");
        } else if (prompt.equals("list")) {
            return new TextCommand("list");

        } else if (prompt.startsWith("mark")) {
            int index = Integer.parseInt(prompt.substring(5));
            return new MarkCommand("mark", index);
        } else if (prompt.startsWith("unmark")) {
            int index = Integer.parseInt(prompt.substring(7));
            return new MarkCommand("unmark", index);

        } else if (prompt.startsWith("todo")) {
            return new CreateCommand("todo", prompt);
        } else if (prompt.startsWith("deadline")) {
            return new CreateCommand("deadline", prompt);
        } else if (prompt.startsWith("event")) {
            return new CreateCommand("event", prompt);

        } else if (prompt.startsWith("delete")) {
            int index = Integer.parseInt(prompt.substring(7));
            return new DeleteCommand("delete", index);
        } else {
            throw new InvalidCommandException(prompt);
        }
    }
}
