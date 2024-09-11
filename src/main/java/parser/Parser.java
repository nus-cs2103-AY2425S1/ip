package parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import commands.ByeCommand;
import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.TodoCommand;
import commands.UnmarkCommand;
import exception.PrimoException;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.ToDoTask;

/**
 * The Parser class is responsible for interpreting user commands and converting
 * them into corresponding {@link Command} objects. It parses the input command string
 * and creates commands like {@link TodoCommand}, {@link DeadlineCommand}, {@link EventCommand},
 * {@link MarkCommand}, {@link UnmarkCommand}, and {@link DeleteCommand} based on the command type.
 */
public class Parser {

    /**
     * Enum to represent different types of commands.
     */
    enum CommandType {
        BYE("bye"),
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DELETE("delete"),
        FIND("find");

        private String command;

        CommandType(String command) {
            this.command = command;
        }

        /**
         * Gets the CommandType corresponding to the given command string.
         *
         * @param s The command string to be matched with an enum value.
         * @return The corresponding CommandType.
         * @throws PrimoException If the command string does not match any known command type.
         */
        public static CommandType getCommandType(String s) throws PrimoException {
            for (CommandType type : values()) {
                if (type.command.equals(s)) {
                    return type;
                }
            }
            throw new PrimoException("Invalid command!\n(Expected Commands: todo, deadline, event, mark, unmark, "
                    + "delete, list, find, bye)\n");
        }
    }

    /**
     * Parses the full command string and returns the appropriate {@link Command} object.
     *
     * @param fullCommand The full command string input by the user.
     * @return The corresponding Command object based on the parsed command string.
     * @throws PrimoException If the command format is invalid or parameters are missing/incorrect.
     */
    public static Command parse(String fullCommand) throws PrimoException {
        // Split the full command into parts
        String[] words = fullCommand.split(" ");
        // Determine the command type
        CommandType type = CommandType.getCommandType(words[0]);

        switch (type) {
        case BYE:
            assert words[0].equals("bye");
            return new ByeCommand();
        case LIST:
            assert words[0].equals("list");
            return new ListCommand();
        case MARK:
            assert words[0].equals("mark");
            assert words.length > 1;
            try {
                Integer.valueOf(words[1]);
            } catch (NumberFormatException e) {
                throw new PrimoException("mark <integer> expected");
            }
            int markIndex = Integer.valueOf(words[1]) - 1;
            return new MarkCommand(markIndex);
        case UNMARK:
            assert words[0].equals("unmark");
            assert words.length > 1;
            try {
                Integer.valueOf(words[1]);
            } catch (NumberFormatException e) {
                throw new PrimoException("unmark <integer> expected");
            }
            int unmarkIndex = Integer.valueOf(words[1]) - 1;
            return new UnmarkCommand(unmarkIndex);
        case TODO:
            assert words[0].equals("todo");
            assert words.length >= 2;
            int todoFromIndex = fullCommand.indexOf("todo ") + 5;
            String todoDescription = fullCommand.substring(todoFromIndex).trim();
            if (todoDescription.isEmpty()) {
                throw new PrimoException("Description cannot be empty! Expected: todo <string>");
            }
            ToDoTask newToDoTask = new ToDoTask(todoDescription);
            return new TodoCommand(newToDoTask);
        case DEADLINE:
            assert words[0].equals("deadline");
            assert words.length >= 3;
            if (!fullCommand.contains("/by")) {
                throw new PrimoException("Invalid parameters! Expected: deadline <string> /by <string>");
            }
            int deadlineFromIndex = fullCommand.indexOf("deadline ") + 9;
            int deadlineToIndex = fullCommand.indexOf("/by");
            String deadlineDescription = fullCommand.substring(deadlineFromIndex, deadlineToIndex).trim();
            if (deadlineDescription.isEmpty()) {
                throw new PrimoException("Description cannot be empty! Expected deadline <string> /by <string>");
            }

            String dueTime = fullCommand.substring(deadlineToIndex + 3).trim();
            LocalDate parsedDate = null;
            try {
                parsedDate = LocalDate.parse(dueTime);
            } catch (DateTimeParseException e) {
                System.out.println("Deadline not in the form of YYYY-MM-DD or invalid DATE");
                dueTime = "";
            }
            if (dueTime.isEmpty()) {
                throw new PrimoException("deadline time empty or wrong formatting! Expected deadline <string> "
                        + "/by YYYY-MM-DD");
            }
            DeadlineTask newDeadlineTask = new DeadlineTask(deadlineDescription, parsedDate);
            return new DeadlineCommand(newDeadlineTask);
        case EVENT:
            assert words[0].equals("event");
            assert words.length >= 4;
            if (!fullCommand.contains("/from") || !fullCommand.contains("/to")) {
                throw new PrimoException("Invalid parameters! Expected: event <string> /from <string> /to <string>");
            }
            int eventFromIndex = fullCommand.indexOf("event ") + 6;
            int eventToIndex = fullCommand.indexOf("/from");
            int eventFinalIndex = fullCommand.indexOf("/to");
            String eventDescription = fullCommand.substring(eventFromIndex, eventToIndex).trim();
            if (eventDescription.isEmpty()) {
                throw new PrimoException("Description cannot be empty! Expected deadline <string> /by <string>");
            }
            String from = fullCommand.substring(eventToIndex + 5, eventFinalIndex).trim();
            LocalDate parsedFromDate = null;
            LocalDate parsedToDate = null;
            try {
                parsedFromDate = LocalDate.parse(from);
            } catch (DateTimeParseException e) {
                System.out.println("\"From\" date not in the form of YYYY-MM-DD or invalid DATE");
                from = "";
            }
            if (from.isEmpty()) {
                throw new PrimoException("'From' parameter empty or wrong formatting! Expected event "
                        + "/from YYYY-MM-DD /to YYYY-MM-DD");
            }
            String to = fullCommand.substring(eventFinalIndex + 3).trim();
            try {
                parsedToDate = LocalDate.parse(to);
            } catch (DateTimeParseException e) {
                System.out.println("\"To\" date not in the form of YYYY-MM-DD or invalid DATE");
                to = "";
            }
            if (to.isEmpty()) {
                throw new PrimoException("'To' parameter cannot be empty! Expected deadline YYYY-MM-DD /by YYYY-MM-DD");
            }
            EventTask newEventTask = new EventTask(eventDescription, parsedFromDate, parsedToDate);
            return new EventCommand(newEventTask);
        case DELETE:
            assert words[0].equals("delete");
            assert words.length > 1;
            try {
                Integer.valueOf(words[1]);
            } catch (NumberFormatException e) {
                throw new PrimoException("delete <integer> expected");
            }
            int deleteIndex = Integer.valueOf(words[1]) - 1;
            return new DeleteCommand(deleteIndex);
        case FIND:
            assert words[0].equals("find");
            assert words.length > 1;
            String wordToFind = "";
            if (words.length <= 1) {
                throw new PrimoException("Poor formatting! Expecting find <string>");
            }
            wordToFind = words[1];
            return new FindCommand(wordToFind);
        default:
            return null; // should not reach here if exception handling is correct
        }
    }
}
