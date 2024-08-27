package applemazer;

import commands.*;

public class Parser {

    /**
     * Parses additional information from the user input based on the given command.
     * @param command Command that the user inputs.
     * @return Command object that is ready to execute the user command.
     * @throws Exception Thrown when parsing fails due to incorrect user input format.
     */
    public static Command parse(String command) throws Exception {
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
                String desc = parseTodoCommand();
                yield new TodoCommand(desc);
            }
            case "deadline" -> {
                split = parseDeadlineCommand();
                yield new DeadlineCommand(split[0], split[1]);
            }
            case "event" -> {
                split = parseEventCommand();
                yield new EventCommand(split[0], split[1], split[2]);
            }
            case "delete" -> {
                taskNumber = parseIntegerCommand(Command.IntegerCommands.Delete);
                yield new IntegerCommand(Command.IntegerCommands.Delete, taskNumber);
            }
            default -> {
                parseNonExistentCommand();
                yield new NonExistentCommand();
            }
        };
    }

    private static void parseByeCommand() throws Exception {
        String desc = Applemazer.sc.nextLine().trim();
        if (!desc.isEmpty()) {
            throw new Exception("OOPS!!! The description of bye should be empty. ");
        }
    }

    private static void parseListCommand() throws Exception {
        String desc = Applemazer.sc.nextLine().trim();
        if (!desc.isEmpty()) {
            throw new Exception("OOPS!!! The description of list should be empty. ");
        }
    }

    private static int parseIntegerCommand(Command.IntegerCommands command) throws Exception {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(Applemazer.sc.nextLine().trim())-1; // Will throw error if non-integer or no input.
        } catch (Exception e) {
            String errorMessage = String.format("""
                                                OOPS!!! You either have a non-integer input or no input at all.
                                                Try '%s <task number>'.
                                                """, command.toString().toLowerCase());
            throw new Exception(errorMessage);
        }
        return taskNumber;
    }

    private static String parseTodoCommand() throws Exception {
        String desc = Applemazer.sc.nextLine().trim();
        if (desc.isEmpty()) {
            throw new Exception("""
                                OOPS!!! The description of a todo cannot be empty.
                                Try todo <description>.
                                """);
        }
        return desc;
    }

    private static String[] parseDeadlineCommand() throws IndexOutOfBoundsException {
        String[] split;
        try {
            split = Applemazer.sc.nextLine().split("/by", 2);
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

    private static String[] parseEventCommand() throws Exception {
        String[] split;
        try {
            String command = Applemazer.sc.nextLine();
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

    private static void parseNonExistentCommand() throws Exception {
        Applemazer.sc.nextLine(); // Prevents parsing next word which duplicates message.
        String errorMessage = "OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        throw new Exception(errorMessage);
    }
}
