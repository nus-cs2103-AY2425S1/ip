package bob;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bob.command.AddCommand;
import bob.command.Command;
import bob.command.DeleteCommand;
import bob.command.FindCommand;
import bob.command.HelpCommand;
import bob.command.ListCommand;
import bob.command.MarkCommand;
import bob.command.MatchListCommand;
import bob.command.SummariseCommand;
import bob.command.SummariseRangeCommand;
import bob.command.SummariseWeekCommand;
import bob.command.UnmarkCommand;
import bob.exception.EmptyFieldException;
import bob.exception.InvalidCommandException;
import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.ToDo;

/**
 * Represents Bob's parser.
 */
public class Parser {
    /**
     * Parses user input and returns a command object.
     *
     * @param input User's input.
     * @return Command.
     * @throws InvalidCommandException if input doesn't start with any valid commands.
     */
    public Command parse(String input) throws InvalidCommandException, EmptyFieldException {

        if (input.startsWith("list")) {
            if (input.equals("list")) {
                return new ListCommand();
            }
            return new MatchListCommand();
        }
        if (input.startsWith("mark")) {
            String arg = input.substring("mark".length()).trim();
            if (arg.isEmpty()) {
                throw new EmptyFieldException();
            }
            return new MarkCommand(Integer.parseInt(arg) - 1);
        }

        if (input.startsWith("unmark")) {
            String arg = input.substring("unmark".length()).trim();
            if (arg.isEmpty()) {
                throw new EmptyFieldException();
            }
            return new UnmarkCommand(Integer.parseInt(arg) - 1);
        }

        if (input.equals("I need help.")) {
            return new HelpCommand();
        }

        if (input.startsWith("delete")) {
            String arg = input.substring("delete".length()).trim();
            if (arg.isEmpty()) {
                throw new EmptyFieldException();
            }
            return new DeleteCommand(Integer.parseInt(arg) - 1);
        }

        if (input.startsWith("find")) {
            String arg = input.substring("find".length()).trim();
            if (arg.isEmpty()) {
                throw new EmptyFieldException();
            }
            return new FindCommand(arg);
        }


        if (input.startsWith("deadline") || input.startsWith("todo") || input.startsWith("event")) {
            Task task = null;
            if (input.startsWith("deadline")) {
                String by = input.substring(input.indexOf("/by") + "/by".length()).trim();
                String description = input.substring(
                        "deadline".length(),
                        input.indexOf("/by")).trim();
                if (by.isEmpty() || description.isEmpty()) {
                    throw new EmptyFieldException();
                }
                task = new Deadline(description, parseDatetime(by));
            } else if (input.startsWith("todo")) {
                String description = input.substring(
                        "todo".length()).trim();
                if (description.isEmpty()) {
                    throw new EmptyFieldException();
                }
                task = new ToDo(description);
            } else if (input.startsWith("event")) {
                String from = input.substring(
                        input.indexOf("/from") + "/from".length(),
                        input.indexOf("/to")).trim();
                String to = input.substring(input.indexOf("/to") + "/to".length()).trim();
                String description = input.substring(
                        "event".length(),
                        input.indexOf("/from")).trim();
                if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                    throw new EmptyFieldException();
                }
                task = new Event(description, parseDatetime(from), parseDatetime(to));

            }
            return new AddCommand(task);
        }

        if (input.startsWith("summarise")) {
            if (input.equals("summarise week")) {
                return new SummariseWeekCommand();
            }
            String from = input.substring(
                    input.indexOf("/from") + "/from".length(),
                    input.indexOf("/to")).trim();
            String to = input.substring(input.indexOf("/to") + "/to".length()).trim();
            if (from.isEmpty() || to.isEmpty()) {
                throw new EmptyFieldException();
            }
            return new SummariseRangeCommand(parseDatetime(from), parseDatetime(to));
        }
        throw new InvalidCommandException();
    }

    /**
     * Converts input of format "dd/MM/yy HHmm" into a LocalDateTime object
     */
    private LocalDateTime parseDatetime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
        return LocalDateTime.parse(input, formatter);
    }

}