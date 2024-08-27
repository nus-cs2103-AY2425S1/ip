package Kita;

import Kita.Exceptions.*;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private final static Pattern toDoPattern = Pattern.compile("^todo (.+)$");
    private final static Pattern deadlinePattern = Pattern.compile("^deadline (.+) /by (.+)$");
    private final static Pattern eventPattern = Pattern.compile("^event (.+) /from (.+) /to (.+)$");

    public static void parse(String command, Commands commandsExecutor) throws KitaError, IOException {
        if (command.equals("bye")) {
            commandsExecutor.bye();
        } else if (command.equals("list")) {
           commandsExecutor.list();
        } else if (command.startsWith("mark")) {
            commandsExecutor.mark(command);
        } else if (command.startsWith("unmark")) {
            commandsExecutor.unmark(command);
        } else if (command.startsWith("delete")) {
            commandsExecutor.delete(command);
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
                commandsExecutor.createEvent(command, eventMatcher);

            } else if (command.startsWith("deadline")) {
                if (!deadlineMatcher.matches()) {
                    if (!command.contains("/by")) {
                        throw new KitaMissingBy();
                    }
                    throw new KitaMissingDescription();
                }
                commandsExecutor.createDeadline(command, deadlineMatcher);

            } else if (command.startsWith("todo")) {
                if (!todoMatcher.matches()) {
                    throw new KitaMissingDescription();
                }
                ;

                commandsExecutor.createToDo(command, todoMatcher);
            } else {
                // No valid command found :c
                throw new KitaNotFound();
            }
        }
    }
}
