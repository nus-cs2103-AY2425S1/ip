public class Parser {
    public static Command parse(String stringCommand) throws DumplingException {
        String commandString = stringCommand.split(" ")[0];
        CommandEnum commandEnum;
        try {
            commandEnum = CommandEnum.valueOf(commandString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DumplingException(
                    String.format(
                            "%s is not a valid command!\n" +
                                    "     To list items, use 'list'.\n" +
                                    "     To mark or unmark an item as done, use '<mark/unmark> <item index>'.\n" +
                                    "     To add a new item, use '<todo/deadline/event> <task name> <args>'.",
                            stringCommand
                    )
            );
        }
        switch (commandEnum) {
        case TODO:
            // fall through
        case DEADLINE:
            // fall through
        case EVENT:
            return new AddCommand(stringCommand, commandEnum);
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case MARK:
            // fall through
        case UNMARK:
            try {
                int itemIdx = Integer.parseInt(stringCommand.split(" ")[1]);
                return new MarkCommand(commandEnum, itemIdx);
            } catch (NumberFormatException e) {
                throw new DumplingException(
                        "There was an issue when marking / unmarking a task! The argument provided was not a number.");
            } catch (IndexOutOfBoundsException e) {
                throw new DumplingException(
                        "There was an issue with indexing! Try listing the items first!");
            }
        case DELETE:
            try {
                int itemIdx = Integer.parseInt(stringCommand.split(" ")[1]);
                return new DeleteCommand(itemIdx);
            } catch (NumberFormatException e) {
                throw new DumplingException(
                        "There was an issue when marking / unmarking a task! The argument provided was not a number.");
            } catch (IndexOutOfBoundsException e) {
                throw new DumplingException(
                        "There was an issue with indexing! Try listing the items first!");
            }
        default:
            throw new DumplingException("An invalid command was given! Try again.");
        }
    }

    public static Pair<String, Integer> formSubSection(
            String[] splitDescrption,
            int startIdx,
            String terminalString
    ) {
        String formedSection = "";
        int currIdx = startIdx;
        while (currIdx < splitDescrption.length && !splitDescrption[currIdx].equals(terminalString)) {
            if (!formedSection.isEmpty()) {
                formedSection += " ";
            }
            formedSection += splitDescrption[currIdx];
            currIdx++;
        }
        return new Pair<>(formedSection, currIdx);
    }
}
