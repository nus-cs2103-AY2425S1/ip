package kita;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kita.exceptions.KitaError;
import kita.exceptions.KitaMissingBy;
import kita.exceptions.KitaMissingDescription;
import kita.exceptions.KitaMissingFrom;
import kita.exceptions.KitaMissingTo;
import kita.exceptions.KitaNotFound;

/**
 * Main Parser class for parsing of user input and calling the respective commands
 */
public class Parser {
    private static final Pattern toDoPattern = Pattern.compile("^todo (.+)$");
    private static final Pattern deadlinePattern = Pattern.compile("^deadline (.+) /by (.+)$");
    private static final Pattern eventPattern = Pattern.compile(
            "^event (?<name>.+) (?:(?:/from (?<from>.+) /to (?<to>.+))|(?:/to (?<to2>.+) /from (?<from2>.+)))$");

    /**
     * Parses a given command and executes commands in the given Commands object based on the result
     *
     * @param command The String command entered
     * @param commandsExecutor The commandsExecutor to execute commands on
     * @returns boolean - Whether the "bye" command was entered
     */
    public static ParserMessage parse(String command, Commands commandsExecutor) throws KitaError, IOException {
        assert commandsExecutor != null;
        assert command.length() > 0 && command != null;

        if (command.equals("bye")) {
            return new ParserMessage(commandsExecutor.bye(), true);
        } else if (command.equals("list")) {
            return new ParserMessage(commandsExecutor.list(), false);
        } else if (command.startsWith("mark")) {
            return new ParserMessage(commandsExecutor.mark(command), false);
        } else if (command.startsWith("unmark")) {
            return new ParserMessage(commandsExecutor.unmark(command), false);
        } else if (command.startsWith("delete")) {
            return new ParserMessage(commandsExecutor.delete(command), false);
        } else if (command.startsWith("find")) {
            return new ParserMessage(commandsExecutor.find(command), false);
        } else {
            Matcher eventMatcher = eventPattern.matcher(command);
            Matcher deadlineMatcher = deadlinePattern.matcher(command);
            Matcher todoMatcher = toDoPattern.matcher(command);

            if (command.startsWith("event")) {
                if (!eventMatcher.matches()) {
                    if (!command.contains("/from")) {
                        throw new KitaMissingFrom();
                    } else if (!command.contains("/to")) {
                        throw new KitaMissingTo();
                    }
                    throw new KitaMissingDescription();
                }
                return new ParserMessage(commandsExecutor.createEvent(eventMatcher), false);

            } else if (command.startsWith("deadline")) {
                if (!deadlineMatcher.matches()) {
                    if (!command.contains("/by")) {
                        throw new KitaMissingBy();
                    }
                    throw new KitaMissingDescription();
                }
                return new ParserMessage(commandsExecutor.createDeadline(deadlineMatcher), false);

            } else if (command.startsWith("todo")) {
                if (!todoMatcher.matches()) {
                    throw new KitaMissingDescription();
                }

                return new ParserMessage(commandsExecutor.createToDo(todoMatcher), false);
            } else {
                // No valid command found :c
                throw new KitaNotFound();
            }
        }
    }
}
