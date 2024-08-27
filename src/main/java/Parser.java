

public class Parser {
    public static Command parse(String fullCommand) throws MiraException {
        String[] commandParts = fullCommand.split(" ", 2); // can only split one time
        String command = commandParts[0];
        String arguments = commandParts.length > 1 ? commandParts[1] : "";

        switch (command) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                int markIdx = Integer.parseInt(arguments);
                return new MarkCommand(markIdx);
            case "unmark":
                int unmarkIdx = Integer.parseInt(arguments);
                return new UnmarkCommand(unmarkIdx);
            case "delete":
                int deleteIdx = Integer.parseInt(arguments);
                return new DeleteCommand(deleteIdx);
            case "todo":
                return new TodoCommand(arguments);
//            case "deadline" -> {
//                String[] deadlineParts = arguments.split("/by", 2);
//                if (deadlineParts.length != 2) {
//                    throw new MiraException("Invalid format. Use: deadline <description> /by <deadline>");
//                }
//                String description = deadlineParts[0].trim();
//                String by = deadlineParts[1].trim();
//                this.tasks.addTask(new Deadline(description, by));
//            }
//            case "event" -> {
//                String[] eventParts = arguments.split("/from|/to");
//                if (eventParts.length != 3) {
//                    throw new MiraException("Invalid format. Use: event <description> /from <start> /to <end>");
//                }
//                String description = eventParts[0].trim();
//                String from = eventParts[1].trim();
//                String to = eventParts[2].trim();
//                this.tasks.addTask(new Event(description, from, to));
//            }
            default:
                throw new MiraException("I'm sorry, I don't understand that command.");
        }
    }
}
