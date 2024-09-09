package spiderman;

public class Parser {
    protected static boolean isExit = false;

    public static String parseInput(String input, TaskList tasks) {
        String[] splitInput = input.split("\\s+");
        String[] splitInputByCommand = input.split("/");

        Command command = new Command();

        return switch (splitInput[0]) {
            case "todo" -> command.todo(splitInputByCommand, tasks);
            case "deadline" -> command.deadline(splitInputByCommand, tasks);
            case "event" -> command.event(splitInputByCommand, tasks);
            case "delete" -> command.delete(splitInput, tasks);
            case "list" -> command.list(tasks);
            case "find" -> command.find(input, tasks);
            case "mark" -> command.mark(splitInput, tasks);
            case "unmark" -> command.unmark(splitInput, tasks);
            case "bye" -> {
                isExit = true;
                yield command.bye();
            }
            default -> "Sorry, I do not understand what you mean. " +
                    "Check the README file for the list of known actions!";
        };
    }

    public static boolean isExit() {
        return isExit;
    }

}
