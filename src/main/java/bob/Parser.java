package bob;

/**
 * The {@code Parser} class is responsible for converting user input into specific commands that the Bob application
 * can execute. It handles the parsing of various commands and their associated parameters.
 */
public class Parser {

    /**
     * Parses the user input command string and returns the corresponding {@code Command} object.
     * If the input is invalid or contains unknown commands, appropriate exceptions are thrown.
     *
     * @param command the user input command string to be parsed.
     * @return the {@code Command} object that corresponds to the user's input.
     * @throws ExtraParamException if extra parameters are provided for a command that does not require them.
     * @throws TaskIndexException if the task index provided is not a valid number.
     * @throws MissingParamException if required parameters for a command are missing.
     * @throws UnknownCommandException if the input command is not recognized.
     */
    public static Command parse(String command) {
        assert command != null: "parse command should not be null";
        String[] words = command.split(" ");
        String firstWord = words[0];

        switch (firstWord) {
        case ("bye"):
            if (words.length > 1) {
                throw new ExtraParamException(command.substring(4));
            }
            return new ExitCommand();
        case ("list"):
            if (words.length > 1) {
                throw new ExtraParamException(command.substring(5));
            }
            return new ListCommand();
        case ("mark"): {
            String[] arguments = Parser.splitInput(words, MarkCommand.params, MarkCommand.paramCount);
            assert arguments.length == MarkCommand.paramCount: "arguments should have correct number of elements";
            int idx;
            try {
                idx = Integer.parseInt(arguments[0]);
            } catch (NumberFormatException e) {
                throw new TaskIndexException(arguments[0]);
            }
            return new MarkCommand(idx);
        }
        case ("unmark"): {
            String[] arguments = Parser.splitInput(words, UnmarkCommand.params, UnmarkCommand.paramCount);
            assert arguments.length == UnmarkCommand.paramCount: "arguments should have correct number of elements";
            int idx;
            try {
                idx = Integer.parseInt(arguments[0]);
            } catch (NumberFormatException e) {
                throw new TaskIndexException(arguments[0]);
            }
            return new UnmarkCommand(idx);
        }
        case ("find"): {
            String[] arguments = Parser.splitInput(words, FindCommand.params, FindCommand.paramCount);
            assert arguments.length == FindCommand.paramCount: "arguments should have correct number of elements";
            return new FindCommand(arguments[0]);
        }
        case ("findDate"): {
            String[] arguments = Parser.splitInput(words, FindDateCommand.params, FindDateCommand.paramCount);
            assert arguments.length == FindDateCommand.paramCount: "arguments should have correct number of elements";
            return new FindDateCommand(arguments[0]);
        }
        case ("todo"): {
            String[] arguments = Parser.splitInput(words, TodoCommand.params, TodoCommand.paramCount);
            assert arguments.length == TodoCommand.paramCount: "arguments should have correct number of elements";
            return new TodoCommand(arguments[0]);
        }
        case ("deadline"): {
            String[] arguments = Parser.splitInput(words, DeadlineCommand.params, DeadlineCommand.paramCount);
            assert arguments.length == DeadlineCommand.paramCount: "arguments should have correct number of elements";
            return new DeadlineCommand(arguments[0], arguments[1]);
        }
        case ("event"): {
            String[] arguments = Parser.splitInput(words, EventCommand.params, EventCommand.paramCount);
            assert arguments.length == EventCommand.paramCount: "arguments should have correct number of elements";
            return new EventCommand(arguments[0], arguments[1], arguments[2]);
        }
        case ("delete"):
            String[] arguments = Parser.splitInput(words, DeleteCommand.params, DeleteCommand.paramCount);
            assert arguments.length == DeleteCommand.paramCount: "arguments should have correct number of elements";
            int idx;
            try {
                idx = Integer.parseInt(arguments[0]);
            } catch (NumberFormatException e) {
                throw new TaskIndexException(arguments[0]);
            }
            return new DeleteCommand(idx);
        default:
            throw new UnknownCommandException(words[0]);
        }
    }

    /**
     * Splits the user input into components based on specified split parameters.
     * The method identifies and returns the arguments for each command.
     *
     * @param input the user input array split by spaces.
     * @param splits array of parameters to split the input by.
     * @param splitCount the expected number of splits/arguments.
     * @return an array of strings containing the arguments for the command.
     * @throws MissingParamException if required parameters for the command are missing.
     * @throws ExtraParamException if extra parameters for the command are provided.
     */
    public static String[] splitInput(String[] input, String[] splits, int splitCount) {
        assert (input != null) && (splits != null): "String inputs should not be null";
        assert splitCount <= splits.length: "splitCount should not be more than splits.length";
        String[] result = new String[splits.length];
        int[] indexes = new int[splits.length + 1];
        indexes[splits.length] = input.length;

        // find index of each split/parameter in the input array
        for (int i = 0; i < splits.length; i++) {
            int index = Parser.findIndex(input, splits[i], 0);
            assert index < input.length: "argument index should be < input.length";
            indexes[i] = index;
            result[i] = "";
        }

        // detect if any parameters are missing
        for (int i = 0; i < splits.length; i++) {
            if (indexes[i] < 0 || indexes[i + 1] - indexes[i] <= 1) {
                throw new MissingParamException(splits[i]);
            }
        }

        for (int i = 0; i < splits.length; i++) {
            StringBuilder arg = new StringBuilder(input[indexes[i] + 1]);
            for (int j = indexes[i] + 2; j < indexes[i + 1]; j++) {
                arg.append(' ');
                arg.append(input[j]);
            }
            result[i] = arg.toString();
        }

        if (result.length < splitCount) {
            throw new MissingParamException("mark");
        } else if (result.length > splitCount) {
            throw new ExtraParamException("mark");
        }

        return result;
    }

    private static int findIndex(String[] input, String target, int startIndex) {
        assert startIndex >= 0: "startIndex should be non-negative";
        assert input.length > startIndex: "startIndex should be a valid index within input";
        for (int i = startIndex; i < input.length; i++) {
            if (input[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }
}
