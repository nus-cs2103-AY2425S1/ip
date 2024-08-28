
import java.time.LocalDate;

public class Parser {

    public Command parse(String userInput) throws LeBronException {
        String[] words = userInput.split(" ", 2);
        String commandWord = words[0];

        switch (commandWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "delete":
                int index = Integer.parseInt(words[1]);
                return new DeleteCommand(index);
            case "mark":
                index = Integer.parseInt(words[1]);
                return new MarkCommand(index);
            case "unmark":
                index = Integer.parseInt(words[1]);
                return new UnmarkCommand(index);
            case "todo":
                ToDos todo = new ToDos(words[1].trim());
                return new AddCommand(todo);
            case "deadline":
                String[] splStr = words[1].split("/by ", 2);
                LocalDate date = LocalDate.parse(splStr[1]);
                Deadlines deadline = new Deadlines(splStr[0].trim(), date);
                return new AddCommand(deadline);
            case "event":
                if (words[1].contains("/from")) {
                    String[] splStrings = words[1].split("/from", 2);
                    String taskDescription = splStrings[0].trim();
                    LocalDate start = LocalDate.now();
                    LocalDate end = LocalDate.now();
                    if (splStrings[1].contains("/to")) {
                        String[] timeParts = splStrings[1].split("/to", 2);
                        start = LocalDate.parse(timeParts[0].trim());
                        end = LocalDate.parse(timeParts[1].trim());
                    }
                    Event event = new Event(taskDescription, start, end);
                    return new AddCommand(event);
                }

            default:
                throw new LeBronException("What do you mean bro?");
        }
    }
}
