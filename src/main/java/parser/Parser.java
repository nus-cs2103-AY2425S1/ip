package parser;

import commands.*;
import exception.PrimoException;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.ToDoTask;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    // Deals with making sense of the user command
    enum CommandType {
        BYE("bye"),
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DELETE("delete");

        private String command;
        CommandType(String command) {
            this.command = command;
        }

        public static CommandType getCommandType(String s) throws PrimoException {
            for (CommandType type : values()) {
                if (type.command.equals(s)) {
                    return type;
                }
            }
            throw new PrimoException("Invalid command!\n(Expected Commands: todo, deadline, event, mark, unmark, delete, list, bye)\n");
        }
    }

    public static Command parse(String fullCommand) throws PrimoException {
        // throw error when wrong formats
        String[] words = fullCommand.split(" ");
        CommandType type = CommandType.getCommandType(words[0]);
        switch (type) {
            case BYE:
                return new ByeCommand();
            case LIST:
                return new ListCommand();
            case MARK:
                try {
                    Integer.valueOf(words[1]);
                } catch (NumberFormatException e) {
                    throw new PrimoException("mark <integer> expected");
                }
                int markIndex = Integer.valueOf(words[1]) - 1;
                return new MarkCommand(markIndex);
            case UNMARK:
                try {
                    Integer.valueOf(words[1]);
                } catch (NumberFormatException e) {
                    throw new PrimoException("unmark <integer> expected");
                }
                int unmarkIndex = Integer.valueOf(words[1]) - 1;
                return new UnmarkCommand(unmarkIndex);
            case TODO:
                int todoFromIndex = fullCommand.indexOf("todo ") + 5;
                String todoDescription = fullCommand.substring(todoFromIndex).trim();
                if (todoDescription.isEmpty()) {
                    throw new PrimoException("Description cannot be empty! Expected: todo <string>");
                }
                ToDoTask newToDoTask = new ToDoTask(todoDescription);
                return new TodoCommand(newToDoTask);
            case DEADLINE:
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
                    throw new PrimoException("deadline time empty or wrong formatting! Expected deadline <string> " +
                            "/by YYYY-MM-DD");
                }
                DeadlineTask newDeadlineTask = new DeadlineTask(deadlineDescription, parsedDate);
                return new DeadlineCommand(newDeadlineTask);
            case EVENT:
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
                    throw new PrimoException("'From' parameter empty or wrong formatting! Expected event /from YYYY-MM-DD /to YYYY-MM-DD");
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
                try {
                    Integer.valueOf(words[1]);
                } catch (NumberFormatException e) {
                    throw new PrimoException("delete <integer> expected");
                }
                int deleteIndex = Integer.valueOf(words[1]) - 1;
                return new DeleteCommand(deleteIndex);
        }
        return null; // should not reach here if exception handling is great
    }
}
