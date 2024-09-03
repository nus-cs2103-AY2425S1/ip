package spiderman;

public class Parser {
    protected static boolean isExit = false;

    public static void parseInput(String input, TaskList tasks) {
        String[] splitInput = input.split("\\s+");
        String[] splitInputByCommand = input.split("/");

        Command command = new Command();

        switch(splitInput[0]) {
        case "todo":
            command.todo(splitInputByCommand, tasks);
            break;
        case "deadline":
            command.deadline(splitInputByCommand, tasks);
            break;
        case "event":
            command.event(splitInputByCommand, tasks);
            break;
        case "delete":
            command.delete(splitInput, tasks);
            break;
        case "list":
            command.list(tasks);
            break;
        case "find":
            command.find(input, tasks);
            break;
        case "mark":
            command.mark(splitInput, tasks);
            break;
        case "unmark":
            command.unmark(splitInput, tasks);
            break;
        case "bye":
            isExit = true;
            command.bye();
            break;
        default:
            System.out.println("Sorry, I do not understand what you mean. " +
                    "Check the README file for the list of known actions!");
        }
    }

    public static boolean isExit() {
        return isExit;
    }

}
