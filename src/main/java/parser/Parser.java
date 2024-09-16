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
import tasks.TodoTask;

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
                    + "delete, list, find, bye) (NEW: Try adding /n <note> at the back of command!)\n");
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
        String[] wordsOfCommand = fullCommand.split(" ");
        CommandType type = CommandType.getCommandType(wordsOfCommand[0]);

        switch (type) {
        case BYE:
            assert wordsOfCommand[0].equals("bye");
            return new ByeCommand();
        case LIST:
            assert wordsOfCommand[0].equals("list");
            return new ListCommand();
        case MARK:
            return processMarkCommand(wordsOfCommand);
        case UNMARK:
            return processUnmarkCommand(wordsOfCommand);
        case TODO:
            return processTodoCommand(fullCommand);
        case DEADLINE:
            return processDeadlineCommand(fullCommand);
        case EVENT:
            return processEventCommand(fullCommand);
        case DELETE:
            return processDeleteCommand(wordsOfCommand);
        case FIND:
            return processFindCommand(wordsOfCommand);
        default:
            return null; // should not reach here if command type is valid
        }
    }

    private static FindCommand processFindCommand(String[] wordsOfCommand) throws PrimoException {
        assert wordsOfCommand[0].equals("find");
        assert wordsOfCommand.length > 1;
        if (wordsOfCommand.length <= 1) {
            throw new PrimoException("Poor formatting! Expecting find <string>");
        }
        return new FindCommand(wordsOfCommand[1]);
    }

    private static DeleteCommand processDeleteCommand(String[] wordsOfCommand) throws PrimoException {
        assert wordsOfCommand[0].equals("delete");
        assert wordsOfCommand.length > 1;
        try {
            Integer.valueOf(wordsOfCommand[1]);
        } catch (NumberFormatException e) {
            throw new PrimoException("delete <integer> expected");
        }
        int deleteIndex = Integer.valueOf(wordsOfCommand[1]) - 1;
        return new DeleteCommand(deleteIndex);
    }

    private static EventCommand processEventCommand(String fullCommand) throws PrimoException {
        if (fullCommand.contains("/n")) {
            return eventCommandWithNote(fullCommand);
        } else {
            return eventCommandWithoutNote(fullCommand);
        }
    }

    private static EventCommand eventCommandWithNote(String fullCommand) throws PrimoException {
        boolean containsFrom = fullCommand.contains("/from");
        boolean containsTo = fullCommand.contains("/to");
        if (!containsFrom || !containsTo) {     // guard against commands without /from and /to
            throw new PrimoException("Invalid parameters! Expected: event <string> /from <string> /to <string>");
        }

        int eventNameIndex = fullCommand.indexOf("event ");
        int eventFromIndex = fullCommand.indexOf("/from");
        int eventToIndex = fullCommand.indexOf("/to");
        int eventNoteIndex = fullCommand.indexOf("/n");

        String eventDescription = fullCommand.substring(eventNameIndex + 6, eventFromIndex).trim();
        String eventNote = fullCommand.substring(eventNoteIndex + 2).trim();
        String eventFromDateString = fullCommand.substring(eventFromIndex + 5, eventToIndex).trim();
        String eventToDateString = fullCommand.substring(eventToIndex + 3, eventNoteIndex).trim();

        if (eventDescription.isEmpty()) {
            throw new PrimoException("Description cannot be empty! Expected deadline <string> /by <string>");
        }
        if (eventFromDateString.isEmpty()) {
            throw new PrimoException("'From' parameter empty or wrong formatting! Expected event "
                    + "/from YYYY-MM-DD /to YYYY-MM-DD");
        }
        if (eventToDateString.isEmpty()) {
            throw new PrimoException("'To' parameter cannot be empty! Expected deadline YYYY-MM-DD /by YYYY-MM-DD");
        }

        EventTask newEventTask;
        try {
            newEventTask = new EventTask(eventDescription, eventFromDateString, eventToDateString, eventNote);
        } catch (DateTimeParseException e) {
            throw new PrimoException("Date formats are not in the form of YYYY-MM-DD");
        }
        return new EventCommand(newEventTask);
    }

    private static EventCommand eventCommandWithoutNote(String fullCommand) throws PrimoException {
        boolean containsFrom = fullCommand.contains("/from");
        boolean containsTo = fullCommand.contains("/to");
        if (!containsFrom || !containsTo) {     // guard against commands without /from and /to
            throw new PrimoException("Invalid parameters! Expected: event <string> /from <string> /to <string>");
        }

        int eventNameIndex = fullCommand.indexOf("event ");
        int eventFromIndex = fullCommand.indexOf("/from");
        int eventToIndex = fullCommand.indexOf("/to");

        String eventDescription = fullCommand.substring(eventNameIndex + 6, eventFromIndex).trim();
        String eventFromDateString = fullCommand.substring(eventFromIndex + 5, eventToIndex).trim();
        String eventToDateString = fullCommand.substring(eventToIndex + 3).trim();

        if (eventDescription.isEmpty()) {
            throw new PrimoException("Description cannot be empty! Expected deadline <string> /by <string>");
        }
        if (eventFromDateString.isEmpty()) {
            throw new PrimoException("'From' parameter empty or wrong formatting! Expected event "
                    + "/from YYYY-MM-DD /to YYYY-MM-DD");
        }
        if (eventToDateString.isEmpty()) {
            throw new PrimoException("'To' parameter cannot be empty! Expected deadline YYYY-MM-DD /by YYYY-MM-DD");
        }

        EventTask newEventTask;
        try {
            newEventTask = new EventTask(eventDescription, eventFromDateString, eventToDateString);
        } catch (DateTimeParseException e) {
            throw new PrimoException("Date formats are not in the form of YYYY-MM-DD");
        }
        return new EventCommand(newEventTask);
    }

    private static DeadlineCommand processDeadlineCommand(String fullCommand) throws PrimoException {
        if (fullCommand.contains("/n")) {
            return deadlineCommandWithNote(fullCommand);
        } else {
            return deadlineCommandWithoutNote(fullCommand);
        }
    }

    private static DeadlineCommand deadlineCommandWithNote(String fullCommand) throws PrimoException {
        if (!fullCommand.contains("/by")) {     // guard against commands without /by
            throw new PrimoException("Invalid parameters! Expected: deadline <string> /by <string>");
        }

        int deadlineNameIndex = fullCommand.indexOf("deadline ");
        int deadlineByIndex = fullCommand.indexOf("/by");
        int deadlineNoteIndex = fullCommand.indexOf("/n");

        String deadlineDescription = fullCommand.substring(deadlineNameIndex + 9, deadlineByIndex).trim();
        String deadlineDateString = fullCommand.substring(deadlineByIndex + 3, deadlineNoteIndex).trim();
        String deadlineNoteString = fullCommand.substring(deadlineNoteIndex + 2).trim();

        if (deadlineDescription.isEmpty()) {
            throw new PrimoException("Description cannot be empty! Expected deadline <string> /by <string>");
        }
        if (deadlineDateString.isEmpty()) {
            throw new PrimoException("Deadline time empty! Expected deadline <string> "
                    + "/by YYYY-MM-DD");
        }

        DeadlineTask newDeadlineTask;
        try {
            newDeadlineTask = new DeadlineTask(deadlineDescription, deadlineDateString, deadlineNoteString);
        } catch (DateTimeParseException e) {
            throw new PrimoException("Deadline not in the form of YYYY-MM-DD or invalid DATE");
        }
        return new DeadlineCommand(newDeadlineTask);
    }

    private static DeadlineCommand deadlineCommandWithoutNote(String fullCommand) throws PrimoException {
        if (!fullCommand.contains("/by")) {     // guard against commands without /by
            throw new PrimoException("Invalid parameters! Expected: deadline <string> /by <string>");
        }

        int deadlineNameIndex = fullCommand.indexOf("deadline ");
        int deadlineByIndex = fullCommand.indexOf("/by");

        String deadlineDescription = fullCommand.substring(deadlineNameIndex + 9, deadlineByIndex).trim();
        String deadlineDateString = fullCommand.substring(deadlineByIndex + 3).trim();
        if (deadlineDescription.isEmpty()) {
            throw new PrimoException("Description cannot be empty! Expected deadline <string> /by <string>");
        }
        if (deadlineDateString.isEmpty()) {
            throw new PrimoException("Deadline time empty! Expected deadline <string> "
                    + "/by YYYY-MM-DD");
        }

        DeadlineTask newDeadlineTask;
        try {
            newDeadlineTask = new DeadlineTask(deadlineDescription, deadlineDateString);
        } catch (DateTimeParseException e) {
            throw new PrimoException("Deadline not in the form of YYYY-MM-DD or invalid DATE");
        }
        return new DeadlineCommand(newDeadlineTask);
    }

    private static TodoCommand processTodoCommand(String fullCommand) throws PrimoException {
        if (fullCommand.contains("/n")) {
            return todoCommandWithNote(fullCommand);
        } else {
            return todoCommandWithoutNote(fullCommand);
        }
    }

    private static TodoCommand todoCommandWithNote(String fullCommand) throws PrimoException {
        String[] wordsOfCommand = fullCommand.split(" ");
        assert wordsOfCommand[0].equals("todo");
        assert wordsOfCommand.length >= 2;
        int todoNameIndex = fullCommand.indexOf("todo ") + 5;
        int todoNoteIndex = fullCommand.indexOf("/n");
        String todoDescription = fullCommand.substring(todoNameIndex, todoNoteIndex).trim();
        String todoNote = fullCommand.substring(todoNoteIndex + 2).trim();
        if (todoDescription.isEmpty()) {
            throw new PrimoException("Description cannot be empty! Expected: todo <string>");
        }

        TodoTask newTodoTask = new TodoTask(todoDescription, todoNote);
        System.out.println(newTodoTask);
        return new TodoCommand(newTodoTask);
    }

    private static TodoCommand todoCommandWithoutNote(String fullCommand) throws PrimoException {
        String[] wordsOfCommand = fullCommand.split(" ");
        assert wordsOfCommand[0].equals("todo");
        assert wordsOfCommand.length >= 2;
        int todoNameIndex = fullCommand.indexOf("todo ") + 5;
        String todoDescription = fullCommand.substring(todoNameIndex).trim();
        if (todoDescription.isEmpty()) {
            throw new PrimoException("Description cannot be empty! Expected: todo <string>");
        }

        TodoTask newTodoTask = new TodoTask(todoDescription);
        return new TodoCommand(newTodoTask);
    }

    private static UnmarkCommand processUnmarkCommand(String[] wordsOfCommand) throws PrimoException {
        assert wordsOfCommand[0].equals("unmark");
        assert wordsOfCommand.length > 1;
        try {
            Integer.valueOf(wordsOfCommand[1]);
        } catch (NumberFormatException e) {
            throw new PrimoException("unmark <integer> expected");
        }
        int unmarkIndex = Integer.parseInt(wordsOfCommand[1]) - 1;
        return new UnmarkCommand(unmarkIndex);
    }

    private static MarkCommand processMarkCommand(String[] wordsOfCommand) throws PrimoException {
        assert wordsOfCommand[0].equals("mark");
        assert wordsOfCommand.length > 1;
        try {
            Integer.valueOf(wordsOfCommand[1]);
        } catch (NumberFormatException e) {
            throw new PrimoException("mark <integer> expected");
        }
        int markIndex = Integer.parseInt(wordsOfCommand[1]) - 1;
        return new MarkCommand(markIndex);
    }
}
