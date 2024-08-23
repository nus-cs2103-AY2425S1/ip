public class Parser {

    public Parser() {
    }
    public static Command parse(String command) {
        if (command.strip().equalsIgnoreCase(CommandType.BYE.toString().toLowerCase())) {
            return new Command(CommandType.BYE.toString().toLowerCase(), "");
        }
        if (command.strip().equalsIgnoreCase(CommandType.LIST.toString().toLowerCase())) {
            return new Command(
                    CommandType.LIST.toString().toLowerCase(),
                    "");
        }
        else if (command.strip().toLowerCase().contains(CommandType.MARK.toString().toLowerCase())) {
            return new Command(
                    CommandType.MARK.toString().toLowerCase(),
                    command);
        }
        else if (command.strip().toLowerCase().contains(CommandType.DELETE.toString().toLowerCase())) {
            return new Command(
                    CommandType.DELETE.toString().toLowerCase(),
                    command);
        } else if (command.strip().toLowerCase().contains(CommandType.FIND.toString().toLowerCase())) {
            return new Command(
                    CommandType.FIND.toString().toLowerCase(),
                    command);
        } else {
            return new Command(command,command);
        }
    }
}
