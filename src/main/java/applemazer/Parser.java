package applemazer;

import java.util.Scanner;

import commands.ByeCommand;
import commands.Command;
import commands.DeadlineCommand;
import commands.DeadlineDuplicateCommand;
import commands.EventCommand;
import commands.EventDuplicateCommand;
import commands.FindCommand;
import commands.IntegerCommand;
import commands.ListCommand;
import commands.NonExistentCommand;
import commands.TodoCommand;
import commands.TodoDuplicateCommand;
import tasks.DuplicateHandler;

/**
 * Class that parses all user commands.
 */
public class Parser {
    private final Scanner sc;
    private final DuplicateHandler duplicateHandler;

    /**
     * Constructor for a {@code Parser} object.
     * @param sc The {@code Scanner} to read user input from.
     * @param duplicateHandler The {@code DuplicateHandler} that handles instances
     *                         of duplicate tasks being added.
     */
    public Parser(Scanner sc, DuplicateHandler duplicateHandler) {
        this.sc = sc;
        this.duplicateHandler = duplicateHandler;
    }

    /**
     * Parses additional information from the user input based on the given command.
     * @param command Command that the user inputs.
     * @return Command object that is ready to execute the user command.
     * @throws Exception Thrown when parsing fails due to incorrect user input format.
     */
    public Command parse(String command) throws Exception {
        String desc;
        String[] split;
        int taskNumber;

        return switch (command) {
        case "bye" -> {
            parseByeCommand();
            yield new ByeCommand();
        }
        case "list" -> {
            parseListCommand();
            yield new ListCommand();
        }
        case "mark" -> {
            taskNumber = parseIntegerCommand(Command.IntegerCommands.Mark);
            yield new IntegerCommand(Command.IntegerCommands.Mark, taskNumber);
        }
        case "unmark" -> {
            taskNumber = parseIntegerCommand(Command.IntegerCommands.Unmark);
            yield new IntegerCommand(Command.IntegerCommands.Unmark, taskNumber);
        }
        case "todo" -> {
            desc = parseTodoCommand();
            if (duplicateHandler.hasTodoDuplicate(desc)) {
                yield new TodoDuplicateCommand();
            } else {
                yield new TodoCommand(desc);
            }
        }
        case "deadline" -> {
            split = parseDeadlineCommand();
            desc = split[0];
            String deadline = split[1];
            if (duplicateHandler.hasDeadlineDuplicate(desc, deadline)) {
                yield new DeadlineDuplicateCommand();
            } else {
                yield new DeadlineCommand(desc, deadline);
            }
        }
        case "event" -> {
            split = parseEventCommand();
            desc = split[0];
            String from = split[1];
            String to = split[2];
            if (duplicateHandler.hasEventDuplicate(desc, from, to)) {
                yield new EventDuplicateCommand();
            } else {
                yield new EventCommand(split[0], split[1], split[2]);
            }
        }
        case "delete" -> {
            taskNumber = parseIntegerCommand(Command.IntegerCommands.Delete);
            yield new IntegerCommand(Command.IntegerCommands.Delete, taskNumber);
        }
        case "find" -> {
            desc = parseFindCommand();
            yield new FindCommand(desc);
        }
        default -> {
            parseNonExistentCommand();
            yield new NonExistentCommand();
        }
        };
    }

    private void parseByeCommand() throws Exception {
        String desc = sc.nextLine().trim();
        if (!desc.isEmpty()) {
            throw new Exception("OOPS!!! The description of bye should be empty. ");
        }
    }

    private void parseListCommand() throws Exception {
        String desc = sc.nextLine().trim();
        if (!desc.isEmpty()) {
            throw new Exception("OOPS!!! The description of list should be empty. ");
        }
    }

    private int parseIntegerCommand(Command.IntegerCommands command) throws Exception {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(sc.nextLine().trim()) - 1;
        } catch (Exception e) {
            String errorMessage = String.format("""
                                                OOPS!!! You either have a non-integer input or no input at all.
                                                Try '%s <task number>'.
                                                """, command.toString().toLowerCase());
            throw new Exception(errorMessage);
        }
        return taskNumber;
    }

    private String parseTodoCommand() throws Exception {
        String desc = sc.nextLine().trim();
        if (desc.isEmpty()) {
            throw new Exception("""
                                OOPS!!! The description of a todo cannot be empty.
                                Try todo <description>.
                                """);
        }
        return desc;
    }

    private String[] parseDeadlineCommand() throws IndexOutOfBoundsException {
        String[] split;
        try {
            split = sc.nextLine().split("/by", 2);
            for (int i = 0; i < 2; ++i) {
                split[i] = split[i].trim();
            }
        } catch (IndexOutOfBoundsException e) {
            String errorMessage = """
                                  OOPS!!! The description of deadline is wrong.
                                  Try 'deadline <description> /by <yyyy-mm-dd> <HHmm>'
                                        'deadline <description> /by <dd/MM/yyyy> <HHmm>'.
                                  It is not necessary to input the time!
                                  """;
            throw new IndexOutOfBoundsException(errorMessage);
        }
        return split;
    }

    private String[] parseEventCommand() throws Exception {
        String[] split;
        try {
            String command = sc.nextLine();
            int fromIdx = command.indexOf("/from");
            int toIdx = command.indexOf("/to");

            String desc = command.substring(0, fromIdx).trim();
            String from = command.substring(fromIdx + "/from".length(), toIdx).trim();
            String to = command.substring(toIdx + "/to".length()).trim();
            split = new String[]{desc, from, to};
        } catch (IndexOutOfBoundsException e) {
            String errorMessage = """
                                  OOPS!!! The description of event is wrong.
                                  Try 'event <description> /from <date1> /to <date2>'.
                                  <date> should be <yyyy-mm-dd> <HHmm> or <dd/MM/yyyy> <HHmm>.
                                  It is not necessary to input the time!
                                  """;
            throw new Exception(errorMessage);
        }
        return split;
    }

    private String parseFindCommand() throws Exception {
        String desc = sc.nextLine().trim();
        if (desc.isEmpty()) {
            throw new Exception("""
                                OOPS!!! The description of find cannot be empty.
                                Try find <description>.
                                """);
        }
        return desc;
    }

    private void parseNonExistentCommand() throws Exception {
        sc.nextLine(); // Prevents parsing next word which duplicates message.
        String errorMessage = "OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        throw new Exception(errorMessage);
    }
}
