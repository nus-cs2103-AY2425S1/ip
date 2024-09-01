import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.Scanner;

// Deal with making sense of the user commands
public class Parser {

    private static final String[] exitCodes = {"EXIT", "BYE", "GOODBYE", "CLOSE", "QUIT"};

    public static Command parseCommand(String command) throws ParserError {
            String input = command.toUpperCase();

        if (Arrays.asList(exitCodes).contains(input)) {
            return new ExitCommand();
        } else if (input.equals("LIST")) {
            return new ListCommand();
        } else if (input.startsWith("MARK ")) {
            int taskNumber = Integer.parseInt(input.substring(5).trim()) - 1;
            return new MarkTaskCommand(taskNumber, true);
        } else if (input.startsWith("UNMARK ")) {
            int taskNumber = Integer.parseInt(input.substring(7).trim()) - 1;
            return new MarkTaskCommand(taskNumber, false);
        } else if (input.startsWith("TODO")){
            try {
                return new AddTaskCommand(new ToDo(command.substring(5).trim()));
            } catch (IndexOutOfBoundsException e) {
                throw new ParserError("Add a description to your todo!");
            }
        } else if (input.startsWith("DEADLINE")) {
            try {
                int deadlineIndex = input.indexOf("/BY ");
                if (deadlineIndex < 9) {
                    throw new ParserError("Deadline format incorrect. Format: \"deadline [taskName] /by [deadline]\"." +
                            " Try again please");
                }
                if (deadlineIndex == 9) {
                    throw new ParserError("I see the deadline but no task :(");
                }

                return new AddTaskCommand(new Deadline(command.substring(9, deadlineIndex - 1).trim(),
                        command.substring(deadlineIndex + 4).trim()));
            } catch (IndexOutOfBoundsException e) {
                throw new ParserError("You've made a fatal error! Report it to the developer or face eternal DOOM!!");
            }
        } else if (input.startsWith("EVENT")) {
            try {
                int startIndex = input.indexOf("/FROM ");
                int endIndex = input.indexOf("/TO ");
                String parsedStart = command.substring(startIndex + 5, endIndex - 1).trim();
                String parsedEnd = command.substring(endIndex + 4).trim();
                return new AddTaskCommand(new Event(command.substring(6, startIndex - 1), parsedStart, parsedEnd));
            } catch (IndexOutOfBoundsException e) {
                throw new ParserError("Event format incorrect. Format: \"Event [taskName] /from [start] /to [end]\". " +
                        "Try again please");
            }
        } else if (input.startsWith("DELETE") || input.startsWith("REMOVE")) {
            try {
                if (input.substring(7).trim().equals("ALL")) {
                    return new DeleteAllCommand();
                }

                int taskNumber = Integer.parseInt(input.substring(7).trim()) - 1;
                return new DeleteTaskCommand(taskNumber);
            } catch (IndexOutOfBoundsException e) {
                throw new ParserError("Hey, that task doesn't exist for me to delete!");
            } catch (NumberFormatException e) {
                throw new ParserError("Hey, that's not a task number! Give me a number please!");
            }
        } else {
            throw new ParserError("I'm not sure what that means :(");
        }
    }


    public static Task parseTask(String str) throws ParserError {
        Scanner scanner = new Scanner(str);
        scanner.useDelimiter("\\|");
        boolean isComplete;
        String title;
        String task = scanner.next().trim();

        switch(task) {
        case ("Task"):
            isComplete = Boolean.parseBoolean(scanner.next().trim());
            title = scanner.next().trim();
            scanner.close();
            return new Task(title, isComplete);
        case ("Todo"):
            isComplete = Boolean.parseBoolean(scanner.next().trim());
            title = scanner.next().trim();
            scanner.close();
            return new ToDo(title, isComplete);
        case ("Event"):
            isComplete = Boolean.parseBoolean(scanner.next().trim());
            title = scanner.next().trim();
            String start = scanner.next().trim();
            String end = scanner.next().trim();
            scanner.close();
            return new Event(title, start, end, isComplete);
        case ("Deadline"):
            isComplete = Boolean.parseBoolean(scanner.next().trim());
            title = scanner.next().trim();
            String deadline = scanner.next().trim();
            scanner.close();
            return new Deadline(title, deadline, isComplete);
        default:
            //should not ever reach here
            scanner.close();
            throw new ParserError("Reached end of parse task. Invalid task type: " + task);
        }
    }

    private static LocalDateTime dateTimeParser(String dateTime) {

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendOptional(DateTimeFormatter.ISO_DATE_TIME)
                .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("dd/M/yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("d/MM/yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("dd MM yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                .optionalStart()
                .appendOptional(DateTimeFormatter.ofPattern(" HH:mm"))
                .appendOptional(DateTimeFormatter.ofPattern(" HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("HH:mm"))
                .optionalEnd()
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();

        try{
            return LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private static LocalTime timeOnlyParser(String dateTime) {

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendOptional(DateTimeFormatter.ofPattern("HH:mm"))
                .appendOptional(DateTimeFormatter.ofPattern("HHmm"))
                .toFormatter();

        try{
            return LocalTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
