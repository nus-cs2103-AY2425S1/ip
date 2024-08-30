package bob;

public class Parser {
    private static final String[] PARAMS_BYE = new String[] { "bye" };
    private static final String[] PARAMS_LIST = new String[] { "list" };
    private static final String[] PARAMS_MARK = new String[] {"mark"};
    private static final String[] PARAMS_UNMARK = new String[] { "unmark" };
    private static final String[] PARAMS_OCCURS = new String[] { "occurs" };
    private static final String[] PARAMS_TODO = new String[] { "todo" };
    private static final String[] PARAMS_DEADLINE = new String[] { "deadline", "/by" };
    private static final String[] PARAMS_EVENT = new String[] { "event", "/from", "/to" };
    private static final String[] PARAMS_DELETE = new String[] { "delete" };

    public static Command parse(String command) {
        String[] words = command.split(" ");
        String firstWord = words[0];

        switch (firstWord) {
        case ("bye"):
            if (words.length > 1) throw new ExtraParamException(command.substring(4));
            return new ExitCommand();
        case ("list"):
            if (words.length > 1) throw new ExtraParamException(command.substring(5));
            return new ListCommand();
        case ("mark"): {
            String[] arguments = Parser.splitInput(words, new String[]{"mark"});
            int idx;
            try {
                idx = Integer.parseInt(arguments[0]);
            } catch (NumberFormatException e) {
                throw new TaskIndexException(arguments[0]);
            }
//            if (idx <= 0 || idx > bob.Bob.taskList.getSize()) {
//                throw new TaskIndexException(inputs[0]);
//            }
            return new MarkCommand(idx);
        }
        case ("unmark"): {
            String[] arguments = Parser.splitInput(words, new String[]{"unmark"});
            int idx;
            try {
                idx = Integer.parseInt(arguments[0]);
            } catch (NumberFormatException e) {
                throw new TaskIndexException(arguments[0]);
            }
//            if (idx <= 0 || idx > bob.Bob.taskList.getSize()) {
//                throw new TaskIndexException(arguments[0]);
//            }
            return new UnmarkCommand(idx);
        }
        case ("find"): {
            String[] arguments = Parser.splitInput(words, new String[]{"find"});
            return new FindCommand(arguments[0]);
        }
        case ("todo"): {
            String[] arguments = Parser.splitInput(words, new String[]{"todo"});
            return new TodoCommand(arguments[0]);
        }
        case ("deadline"): {
            String[] arguments = Parser.splitInput(words, new String[]{"deadline", "/by"});
            return new DeadlineCommand(arguments[0], arguments[1]);
        }
        case ("event"): {
            String[] arguments = Parser.splitInput(words, new String[]{"event", "/from", "/to"});
            return new EventCommand(arguments[0], arguments[1], arguments[2]);
        }
        case ("delete"):
            String[] arguments = Parser.splitInput(words, new String[]{"delete"});
            int idx;
            try {
                idx = Integer.parseInt(arguments[0]);
            } catch (NumberFormatException e) {
                throw new TaskIndexException(arguments[0]);
            }
//            if (idx <= 0 || idx > bob.Bob.taskList.getSize()) {
//                throw new TaskIndexException(inputs[0]);
//            }
            return new DeleteCommand(idx);
        default:
            throw new UnknownCommandException(words[0]);
        }
    }

    public static String[] splitInput(String[] input, String[] splits) {
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
