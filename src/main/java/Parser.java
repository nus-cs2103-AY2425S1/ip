public class Parser {

    public static String[] splitCommandAndAction(String input) {
        int spaceLocation = input.indexOf(" ");
        String commandString;
        String item;

        if (spaceLocation == -1) {
            commandString = input;
            item = "";
        } else {
            commandString = input.substring(0,spaceLocation);
            item = input.substring(spaceLocation+1);
        }
        return new String[] {commandString, item};
    }

    public static String[] parseDeadline(String item) throws WrongSyntaxForCommandException{
        int spaceLocation = item.indexOf(" /by ");
        if (spaceLocation  == -1) {
            throw new WrongSyntaxForCommandException(Command.DEADLINE);
        }
        String name = item.substring(0,spaceLocation);
        String due = item.substring(spaceLocation + 5);

        return new String[] {name, due};
    }

    public static String[] parseEvent(String item) throws WrongSyntaxForCommandException {
        int fromLocation = item.indexOf(" /from ");
        int toLocation = item.indexOf(" /to ");
        String name, start, end;
        if (fromLocation == -1 || toLocation == -1) {
            throw new WrongSyntaxForCommandException(Command.EVENT);
        }
        if (fromLocation < toLocation) {
            name = item.substring(0,fromLocation);
            start = item.substring(fromLocation + 7, toLocation);
            end = item.substring(toLocation + 5);
        } else {
            name = item.substring(0, toLocation);
            end = item.substring(toLocation + 5, fromLocation);
            start = item.substring(fromLocation + 7);
        }
        return new String[] {name, start, end};
    }
}
