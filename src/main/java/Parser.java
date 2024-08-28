public class Parser {
    public static Command parse(String input) {
        // Split input into command and remaining
        String command = input;
        String remaining = "";
        if (input.contains(" ")) {
            command = input.substring(0, input.indexOf(" "));
            remaining = input.substring(input.indexOf(" ") + 1);
        }

        if (command.equals("todo")) {
            return new AddCommand(remaining, AddCommand.TaskType.TODO);
        } else if (command.equals("deadline")) {
            return new AddCommand(remaining, AddCommand.TaskType.DEADLINE);
        } else if (command.equals("event")) {
            return new AddCommand(remaining, AddCommand.TaskType.EVENT);
        }

        throw new LlamaException("Command not found, try again.");
    }
}
