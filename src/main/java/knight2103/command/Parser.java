package knight2103.command;

import java.util.Optional;

public class Parser {
    /**
     * Interprets user input and creates a relevant command. Returns Optional<Command>.
     * If the input is not interpretable, an empty Optional is returned to
     * indicate that a relevant command cannot be created.
     *
     * @param fullCommand The input command given which must be of String type.
     * @return an Optional containing the Command if command is valid or
     * an empty optional if the Command is invalid.
     * @throws InstructionInvalid If the first word of user input does not match
     * with expected command verbs.
     * @throws ArrayIndexOutOfBoundsException If the user input does not match
     * the expected basic format which for certain commands, requires some
     * description apart from the first word (command verb).
     */
    public static Optional<Command> parse(String fullCommand) throws InstructionInvalid {
        String[] commandArray = fullCommand.split(" ", 2);
        try {
            switch (commandArray[0]) {
                case "list":
                    return Optional.of(new ListCommand());
                case "todo":
                    return Optional.of(new AddCommand(CommandVerb.TODO, commandArray[1]));
                case "deadline":
                    return Optional.of(new AddCommand(CommandVerb.DEADLINE, commandArray[1]));
                case "event":
                    return Optional.of(new AddCommand(CommandVerb.EVENT, commandArray[1]));
                case "mark":
                    return Optional.of(new ModifyCommand(CommandVerb.MARK, commandArray[1]));
                case "unmark":
                    return Optional.of(new ModifyCommand(CommandVerb.UNMARK, commandArray[1]));
                case "delete":
                    return Optional.of(new ModifyCommand(CommandVerb.DELETE, commandArray[1]));
                case "bye":
                    return Optional.of(new ByeCommand());
                default:
                    throw new InstructionInvalid();
            }
        } catch (InstructionInvalid e) {
            System.out.println("Invalid Instruction. Only valid Instructions are list, todo, deadline, event, mark, unmark, delete");
            return Optional.empty();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("There's an issue in the instruction format. Please check that it is <knight2103.command.CommandVerb> <description> format");
            return Optional.empty();
        }
    }
}
