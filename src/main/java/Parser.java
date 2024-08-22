import java.util.HashMap;

public class Parser {
    /**
     * This takes a command split by spaces, and processes them into separate commands
     * The Hashmap returned contains key-value pairs of {parameter name: argument value}
     *
     * @param command A command
     * @return A Command with the parsed arguments.
     */
    public static Command parse(String command) throws ZaibotException {
        HashMap<String, String> arguments = new HashMap<>();

        if (command.indexOf(' ') == -1) {
            return new Command(command, arguments);
        }

        String commandName = command.substring(0, command.indexOf(' '));

        if (commandName.equals("mark") || commandName.equals("unmark") || commandName.equals("delete")) {
            arguments.put("number",
                    command.substring(command.indexOf(" ") + 1));
        }
        else {
            String optionString = command.substring(command.indexOf(' '));
            String[] options = optionString.split(" /");

            if (optionString.isEmpty() || options[0].isEmpty()) {
                throw new ZaibotException("Name cannot be empty.");
            }
            arguments.put("name", options[0].trim());

            for (int i = 1; i < options.length; i++) {
                String option = options[i];
                String optionName = option.substring(0, option.indexOf(' ')).trim();
                String optionValue = option.substring(option.indexOf(' ')).trim();
                if (optionValue.isEmpty()) {
                    throw new ZaibotException(String.format("Option %s cannot be empty.", optionName));
                }
                arguments.put(optionName, optionValue);
            }
        }
        return new Command(commandName, arguments);
    }
}
