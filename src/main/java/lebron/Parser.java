package lebron;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
            case "find":
                String keyword = words[1];
                return new FindCommand(keyword);
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
                    LocalDate[] dates = parseEventDates(splStrings[1]);
                    Event event = new Event(taskDescription, dates[0], dates[1]);
                    return new AddCommand(event);
                }
            default:
                throw new LeBronException("What do you mean bro?");
        }
    }

    private LocalDate[] parseEventDates(String dateRange) {
        LocalDate start = LocalDate.now(); // Default start date
        LocalDate end = LocalDate.now();   // Default end date

        if (dateRange.contains("/to")) {
            String[] timeParts = dateRange.split("/to", 2);
            start = parseDate(timeParts[0].trim());
            end = parseDate(timeParts[1].trim());
        }

        return new LocalDate[]{start, end};
    }

    private LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: " + dateString + ". Using current date as default.");
            return LocalDate.now();
        }
    }
}
