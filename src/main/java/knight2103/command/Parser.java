package knight2103.command;

import java.util.Optional;

public class Parser {
    public static Optional<Command> parse(String fullCommand) {
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
            case "find":
                return Optional.of(new FindCommand(CommandVerb.FIND, commandArray[1]));
            case "bye":
                return Optional.of(new ByeCommand());
            default:
                throw new InstructionInvalid();
            }
        } catch (InstructionInvalid e) {
            System.out.println("Invalid Instruction. Only valid Instructions are "
                    + "list, todo, deadline, event, mark, unmark, delete, find");
            return Optional.empty();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("There's an issue in the instruction format. Please check that it is <knight2103.command.CommandVerb> <description> format");
            return Optional.empty();
        }
    }
}
