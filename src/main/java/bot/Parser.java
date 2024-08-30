package bot;

import bot.enums.Command;
import bot.exceptions.InvalidCommandException;
import bot.exceptions.InvalidTaskDescriptionException;
import bot.tasks.Deadline;
import bot.tasks.Event;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    // TODO: Move this into a Command class
    public static class ParsedInput {
        private final Command cmd;
        private final String args;
        private ParsedInput(Command cmd, String args) {
            this.cmd = cmd;
            this.args = args;
        }

        public Command getCmd() {
            return this.cmd;
        }

        public String getArgs() {
            return this.args;
        }
    }
    public ParsedInput parseInput(String input) throws InvalidCommandException {
        Pattern regex = Pattern.compile("(\\w+)\\s*(.*)");
        Matcher matcher = regex.matcher(input);
        if (matcher.matches()) {
            String cmd = matcher.group(1);
            String args = matcher.group(2);

            return new ParsedInput(Command.fromString(cmd), args);
        } else {
            throw new InvalidCommandException(input);
        }
    }

    public Deadline parseDeadlineTask(String args) throws InvalidTaskDescriptionException {
        Pattern regex = Pattern.compile("(.*)\\s/by\\s(.*)");
        Matcher matcher = regex.matcher(args);
        if (matcher.matches()) {
            String task = matcher.group(1);
            String deadline = matcher.group(2);
            return new Deadline(task, LocalDate.parse(deadline));
        } else {
            throw new InvalidTaskDescriptionException(args);
        }
    }

    public Event parseEventTask(String args) throws InvalidTaskDescriptionException {
        Pattern regex = Pattern.compile("(.*)\\s/from\\s(.*)\\s/to\\s(.*)");
        Matcher matcher = regex.matcher(args);
        if (matcher.matches()) {
            String task = matcher.group(1);
            String from = matcher.group(2);
            String to = matcher.group(3);
            return new Event(task, LocalDate.parse(from), LocalDate.parse(to));
        } else {
            throw new InvalidTaskDescriptionException(args);
        }
    }
}
