package bob;

public class Parser {

    public static Command parse(String command) {
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
            return new FindCommand(arguments[0]);
        }
        case ("findDate"): {
            String[] arguments = Parser.splitInput(words, FindDateCommand.params, FindDateCommand.paramCount);
            return new FindDateCommand(arguments[0]);
        }
        case ("todo"): {
            String[] arguments = Parser.splitInput(words, TodoCommand.params, TodoCommand.paramCount);
            return new TodoCommand(arguments[0]);
        }
        case ("deadline"): {
            String[] arguments = Parser.splitInput(words, DeadlineCommand.params, DeadlineCommand.paramCount);
            return new DeadlineCommand(arguments[0], arguments[1]);
        }
        case ("event"): {
            String[] arguments = Parser.splitInput(words, EventCommand.params, EventCommand.paramCount);
            return new EventCommand(arguments[0], arguments[1], arguments[2]);
        }
        case ("delete"):
            String[] arguments = Parser.splitInput(words, DeleteCommand.params, DeleteCommand.paramCount);
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

    public static String[] splitInput(String[] input, String[] splits, int splitCount) {
        String[] result = new String[splits.length];
        int[] indexes = new int[splits.length + 1];
        indexes[splits.length] = input.length;

        // find index of each split/parameter in the input array
        for (int i = 0; i < splits.length; i++) {
            int index = Parser.findIndex(input, splits[i], 0);
            indexes[i] = index;
            result[i] = "";
        }

        // detect if any parameters are missing
        for (int i = 0; i < splits.length; i++) {
            if (indexes[i] < 0 || indexes[i+1] - indexes[i] <= 1) {
                throw new MissingParamException(splits[i]);
            }
        }

        for (int i = 0; i < splits.length; i++) {
            StringBuilder arg = new StringBuilder(input[indexes[i]+1]);
            for (int j = indexes[i]+2; j < indexes[i+1]; j++) {
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
        for (int i = startIndex;  i < input.length; i++) {
            if (input[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }
}
