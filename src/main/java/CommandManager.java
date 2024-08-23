import java.util.HashMap;

public class CommandManager {

    public CommandManager() {
    }

    private HashMap<String, String> parseLine(String line) {
        String[] words = line.split(" ");
        HashMap<String, String> commandMap = new HashMap<>();
        commandMap.put("command", words[0]);

        if (words.length == 1) {
            return commandMap;
        }

        StringBuilder sb = new StringBuilder(words[1]);
        int i = 2;
        while (i < words.length && !words[i].startsWith("/")) {
            sb.append(" " + words[i]);
            i++;
        }
        commandMap.put("argument", sb.toString());

        if (i == words.length) {
            return commandMap;
        }

        String command = "";
        while (i < words.length) {
            if (words[i].startsWith("/")) {
                if (command != "") {
                    commandMap.put(command, sb.toString());
                }
                command = words[i].substring(1);
                sb = new StringBuilder();
            } else {
                if (sb.length() > 0) {
                    sb.append(" " + words[i]);
                } else {
                    sb.append(words[i]);
                }
            }
            i++;
        }
        commandMap.put(command, sb.toString());

        return commandMap;
    }

    public Command getCommand(String line) throws BarneyException {
        HashMap<String, String> commandMap = parseLine(line);

        switch (commandMap.get("command")) {
            case "list":
                return new ListCommand(commandMap);
            case "mark":
                return new MarkCommand(commandMap, true);
            case "unmark":
                return new MarkCommand(commandMap, false);
            case "todo":
                return new TodoCommand(commandMap);
            case "deadline":
                return new DeadlineCommand(commandMap);
            case "event":
                return new EventCommand(commandMap);
            case "delete":
                return new DeleteCommand(commandMap);
            case "bye":
                return new EndCommand(commandMap);
            default:
                throw new InvalidCommandException("Invalid command, please try again!");
        }
    }
}
