public class Parser {
    public boolean isExit(String command) {
        return command.equalsIgnoreCase("bye");
    }

    public boolean isList(String command) {
        return command.equalsIgnoreCase("list");
    }

    public boolean isMark(String command) {
        if (command.length() >= 4) {
            return command.substring(0, 4).equalsIgnoreCase("mark");
        }
        return false; // If the command is shorter than 4 characters, it cannot be "mark"
    }

    public int getTaskNumber(String command) {
        String[] parts = command.split(" ");
        if (parts.length == 2) {
            try {
                return Integer.parseInt(parts[1]) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Invalid task number, must be more than 0.");
            }
        }
        return -1;
    }

    public boolean isUnmarked(String command) {
        if (command.length() >= 6) {
            return command.substring(0, 6).equalsIgnoreCase("unmark");
        }
        return false; // If the command is shorter than 6 characters, it cannot be "unmark"
    }

    public String echo(String command) {
        return command;
    }
}
