public class Parser {
    public static Command parse(String fullCommand) throws AiException {
        String[] parsedCommand = fullCommand.split(" ", 2);
        String command = parsedCommand[0];
        String arguments = (parsedCommand.length > 1) ? parsedCommand[1] : "";

        switch (command) {
        case "list":
            return new ListCommand();
        case "unmark":
            return new UnmarkCommand(arguments);
        case "mark":
            return new MarkCommand(arguments);
        case "todo":
            if (arguments.length() <= 0) {
                throw new AiException("Whoopsies, todo cannot be empty >.<\n " +
                        "Try something like \"todo hangout with Ai\" instead!\n");
            }
            return new AddCommand(new ToDo(arguments));
        case "deadline":
            if (arguments.length() <= 0) {
                throw new AiException("Whoopsies, deadline cannot be empty >.<\n " +
                        "Try something like \"deadline date w Ai <3 /by Wed\" instead!\n");
            }

            String[] parsedInput = arguments.split(" /by ", 2);
            String desc = parsedInput[0];
            String date = parsedInput[1];

            return new AddCommand(new Deadline(desc, date));
        case "event":
            if (arguments.length() <= 0) {
                throw new AiException("Whoopsies, event cannot be empty >.<\n " +
                        "Try something like \"event birthday w Ai <3333 /from 5am /to 6pm\" instead!\n");
            }
            return new AddCommand(new Event(arguments));
        case "due":
            if (arguments.length() <= 0) {
                throw new AiException("Oh! due cannot be empty!!\n"
                        + "Try something like \"due 2019-12-02\" instead!!!\n");
            }
            return new DueCommand(arguments);
        case "delete":
            return new DeleteCommand(arguments);
        case "bye":
            return new ByeCommand();
        default:
            return new DefaultCommand();
        }
    }
}
