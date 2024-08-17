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

    public Task parseTask(String command) {
        if (command.startsWith("todo")) {
            String description = command.substring(5).trim();
            return new ToDo(description);
        } else if (command.startsWith("deadline")) {
            String[] parts = command.substring(9).split("/by");
            String description = parts[0].trim();
            String by = parts[1].trim();
            return new Deadline(description, by);
        } else if (command.startsWith("event")) {
            String[] parts = command.substring(6).split("/from");
            String description = parts[0].trim();
            String[] timeParts = parts[1].split("/to");
            String from = timeParts[0].trim();
            String to = timeParts[1].trim();
            return new Event(description, from, to);
        } else {
            return null;
        }
    }

    public String echo(String command) {
        return command;
    }
}
