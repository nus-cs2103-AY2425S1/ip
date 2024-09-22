package parser;

/**
 * Parses input from user given by run method in BabyGronk class.
 */
public class Parser {

    /**
     * Formats the input by splitting it and then joining it with single space.
     *
     * @param input String from input.
     * @return Single string with single space between words.
     * @throws EmptyInputException If the string is null or empty.
     */
    public static String parseInput(String input) throws EmptyInputException {
        if (input == null || input.isEmpty()) {
            throw new EmptyInputException("What bro? You're too fanum taxed\n");
        }
        return (String.join(" ", input.strip().split("\\s+")));
    }

    /**
     * Parses input into formatted instructions.
     *
     * @param input Input given from user.
     * @return Instruction object with parsed input.
     * @throws InvalidInputException When input is not valid/defined in project page.
     */
    public Instruction parseInstruction(String input) throws InvalidInputException {
        String[] args = input.toLowerCase().split(" ");
        if (args[0].startsWith("hello") || args[0].startsWith("hi")) {
            return (new Instruction("hi", args));
        }
        if (args[0].startsWith("bye") || args[0].startsWith("exit")) {
            return (new Instruction("bye", args));
        }
        if (Instruction.isListCommand(args[0])) {
            return (new Instruction(args[0], args));
        }
        if (Instruction.isMarkCommand(args[0])) {
            if (args.length < 2) {
                throw new InvalidInputException(InvalidCommands.INVALID_MARK_CMD);
            }
            return (new Instruction(args[0], args));
        }
        if (Instruction.isDeleteCommand(args[0])) {
            if (args.length < 2) {
                throw new InvalidInputException(InvalidCommands.INVALID_DELETE_CMD);
            }
            return (new Instruction(args[0], args));
        }
        if (Instruction.isFindCommand(args[0])) {
            String[] findArgs = input.split(" ", 2);
            return (new Instruction(args[0], findArgs));
        }
        if (Instruction.isTaskCommand(args[0])) {
            if (args.length < 2) {
                throw new InvalidInputException(InvalidCommands.INVALID_TASK_CMD);
            }
            switch (args[0]) {
            case "todo":
                return (createTodoInstruction(input));
            case "deadline":
                return (createDeadlineInstruction(input));
            case "event":
                return (createEventInstruction(input));
            default:
                throw new InvalidInputException(InvalidCommands.UNKNOWN_ERROR); //shouldn't reach here
            }
        }
        throw new InvalidInputException(InvalidCommands.INVALID_INPUT);
    }

    private Instruction createTodoInstruction(String input) {
        String[] todoArgs = input.split(" ", 2);
        assert todoArgs.length == 2;
        return (new Instruction("todo", todoArgs));
    }

    private Instruction createDeadlineInstruction(String input) throws InvalidInputException {
        String[] deadlineArgs = input.split(" ", 2);

        assert deadlineArgs.length == 2;
        String[] deadlineDetails = deadlineArgs[1].split(" /by ");
        if (deadlineDetails.length != 2) {
            throw new InvalidInputException(InvalidCommands.INVALID_DEADLINE);
        }

        String[] deadlineRet = new String[] {deadlineArgs[0], deadlineDetails[0], deadlineDetails[1]};
        return (new Instruction("deadline", deadlineRet));
    }

    private Instruction createEventInstruction(String input) throws InvalidInputException {
        String[] eventArgs = input.split(" ", 2);

        assert eventArgs.length == 2;
        String[] eventDetails = eventArgs[1].split(" /from | /to ");
        if (eventDetails.length != 3) {
            throw new InvalidInputException(InvalidCommands.INVALID_EVENT);
        }

        String[] eventRet = new String[] {eventArgs[0], eventDetails[0], eventDetails[1], eventDetails[2]};
        return (new Instruction("event", eventRet));
    }
}
