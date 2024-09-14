package genji;

import genji.command.Command;
import genji.command.AddCommand;
import genji.command.DateCommand;
import genji.command.DeleteCommand;
import genji.command.ExitCommand;
import genji.command.FindCommand;
import genji.command.ListCommand;
import genji.command.MarkCommand;
import genji.command.UnmarkCommand;
import genji.command.HelpCommand;
import genji.task.ToDo;
import genji.task.Deadline;
import genji.task.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * A class to parse user's commands
 */
public class Parser {

    /**
     * Parses command to exit command
     * @return Exit command
     */
    public static Command parseExit(String command) throws GenjiException{
        if (command.length() > 3) {
            throw new GenjiException("Please don't add things after \"bye\"");
        } else {
            return new ExitCommand();
        }
    }

    /**
     * Parses command to list command
     * @param command User's command to parse
     * @return List command
     * @throws GenjiException When user's command has problem
     */
    public static Command parseList(String command) throws GenjiException {
        if (command.length() > 4) {
            throw new GenjiException("Please don't add things after \"list\"");
        } else {
            return new ListCommand();
        }
    }

    /**
     * Parses command to help command
     * @param command User's command to parse
     * @return Help command
     * @throws GenjiException When user's command has problem
     */
    public static Command parseHelp(String command) throws GenjiException {
        if (command.length() > 4) {
            throw new GenjiException("Please don't add things after \"help\"");
        } else {
            return new HelpCommand();
        }
    }

    /**
     * Parses command to mark command
     * @param command User's command to parse
     * @return Mark command
     * @throws GenjiException When user's command has problem
     */
    public static Command parseMark(String command) throws GenjiException {
        if (command.length() < 6) {
            throw new GenjiException("No descriptions detected " +
                    "or did not enter space, try again");
        } else {
            try {
                int index = Integer.parseInt(command.substring(5)) - 1;
                return new MarkCommand(index);
            } catch (NumberFormatException n) {
                throw new GenjiException("Please input a integer after \"mark\"");
            }
        }
    }

    /**
     * Parses command to unmark command
     * @param command User's command to parse
     * @return Unmark command
     * @throws GenjiException When user's command has problem
     */
    public static Command parseUnmark(String command) throws GenjiException {
        if (command.length() < 8) {
            throw new GenjiException("No descriptions detected " +
                    "or did not enter space, try again");
        } else {
            try {
                int index = Integer.parseInt(command.substring(7)) - 1;
                return new UnmarkCommand(index);
            } catch (NumberFormatException n) {
                throw new GenjiException("Please input a integer after \"unmark\"");
            }
        }
    }

    /**
     * Parses command to add command
     * @param command User's command to parse
     * @return Add command
     * @throws GenjiException When user's command has problem
     */
    public static Command parseTodo(String command) throws GenjiException {
        if (command.length() < 6) {
            throw new GenjiException("No descriptions detected " +
                    "or did not enter space, try again");
        } else {
            ToDo td = new ToDo(command.substring(5));
            return new AddCommand(td);
        }
    }

    /**
     * Parses command to add command
     * @param command User's command to parse
     * @return Add command
     * @throws GenjiException When user's command has problem
     */
    public static Command parseDeadline(String command) throws GenjiException {
        if (command.length() < 10) {
            throw new GenjiException("No descriptions detected, try again");
        } else {
            try {
                String name = command.substring(9, command.lastIndexOf(" /"));
                LocalDateTime time = LocalDateTime.parse(command.substring(command.lastIndexOf(" /") + 5));
                Deadline ddl = new Deadline(name, time);
                return new AddCommand(ddl);
            } catch (StringIndexOutOfBoundsException s) {
                throw new GenjiException("Due date not provided or not in the proper form");
            } catch (DateTimeParseException d) {
                throw new GenjiException("Please provide date time in this format\"yyyy-mm-ddThh:mm\"");
            }
        }
    }

    /**
     * Parses command to add command
     * @param command User's command to parse
     * @return Add command
     * @throws GenjiException When user's command has problem
     */
    public static Command parseEvent(String command) throws GenjiException {
        if (command.length() < 7) {
            throw new GenjiException("No descriptions detected, try again");
        } else {
            try {
                String name = command.substring(6, command.lastIndexOf(" /from"));
                LocalDateTime from = LocalDateTime.parse(command.substring(command.lastIndexOf(" /from") + 7,
                        command.lastIndexOf(" /to")));
                LocalDateTime to = LocalDateTime.parse(command.substring(command.lastIndexOf(" /to") + 5));
                Event evt = new Event(name, from, to);
                return new AddCommand(evt);
            } catch (StringIndexOutOfBoundsException s) {
                throw new GenjiException("Time period not provided or not in the proper form");
            } catch (DateTimeParseException d) {
                throw new GenjiException("Please provide date time in this format\"yyyy-mm-ddThh:mm\"");
            }
        }
    }

    /**
     * Parses command to delete command
     * @param command User's command to parse
     * @return Delete command
     * @throws GenjiException When user's command has problem
     */
    public static Command parseDelete(String command) throws GenjiException {
        if (command.length() < 8) {
            throw new GenjiException("No descriptions detected " +
                    "or did not enter space, try again");
        } else {
            try {
                int index = Integer.parseInt(command.substring(7)) - 1;
                return new DeleteCommand(index);
            } catch (NumberFormatException n) {
                throw new GenjiException("Please input a integer after \"delete\"");
            }
        }
    }

    /**
     * Parses command to date command
     * @param command User's command to parse
     * @return Date command
     * @throws GenjiException When user's command has problem
     */
    public static Command parseDate(String command) throws GenjiException {
        if (command.length() < 6) {
            throw new GenjiException("No descriptions detected, try again");
        } else {
            try {
                String date = command.substring(5);
                LocalDate test = LocalDate.parse(date);
                return new DateCommand(date);
            } catch (DateTimeParseException d) {
                throw new GenjiException("Please provide date time in this format\"yyyy-mm-dd\"");
            }
        }
    }

    /**
     * Parses command to find command
     * @param command User's command to parse
     * @return Find command
     * @throws GenjiException When user's command has problem
     */
    public static Command parseFind(String command) throws GenjiException {
        if (command.length() < 6) {
            throw new GenjiException("No descriptions detected, try again");
        } else {
            String taskDescription = command.substring(5);
            return new FindCommand(taskDescription);
        }
    }

    /**
     *Parses user's command into different types of commands
     * Creates the task if the command ask to
     * @param command user's input
     * @return The corresponding command according to user's input
     * @throws GenjiException When user's command is invalid
     */
    public static Command parse(String command) throws GenjiException {
        if (command.startsWith("bye")) {
            return parseExit(command);
        } else if (command.startsWith("help")) {
            return parseHelp(command);
        } else if (command.startsWith("list")) {
            return parseList(command);
        } else if (command.startsWith("mark")) {
            return parseMark(command);
        } else if (command.startsWith("unmark")) {
            return parseUnmark(command);
        } else if (command.startsWith("todo")) {
            return parseTodo(command);
        } else if (command.startsWith("deadline")) {
            return parseDeadline(command);
        } else if (command.startsWith("event")) {
            return parseEvent(command);
        } else if (command.startsWith("delete")) {
            return parseDelete(command);
        } else if (command.startsWith("date")) {
            return parseDate(command);
        } else if (command.startsWith("find")) {
            return parseFind(command);
        }
        throw new GenjiException("Invalid command, please type \"help\"");
    }
}
