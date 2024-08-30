import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Command parse(String fullCommand) throws LightException {
        Task task = null;
        String[] splitedBySlash;
        String[] splitedBySpace = fullCommand.split("\\s+", 2);
        switch (splitedBySpace[0]) {
            case "bye":
                return new ExitCommand();
            case "mark":
                try {
                    int itemNumber = Integer.parseInt(splitedBySpace[1]) - 1;
                    return new MarkCommand(itemNumber, true);
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
                break;
            case "unmark":
                try {
                    int itemNumber = Integer.parseInt(splitedBySpace[1]) - 1;
                    return new MarkCommand(itemNumber, false);
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
                break;
            case "list":
                return new ListCommand();
            case "delete":
                try {
                    int taskNumber = Integer.parseInt(splitedBySpace[1]) - 1;
                    return new DeleteCommand(taskNumber);
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
                break;
            default:
                switch (splitedBySpace[0]) {
                    case "todo":
                        try {
                            task = new Todo(splitedBySpace[1]);
                        } catch (LightException e) {
                            System.out.println(e);
                        }

                        break;
                    case "deadline":
                        try {
                            splitedBySlash = splitedBySpace[1].split("/by ");
                            task = new Deadline(splitedBySlash[0], splitedBySlash[1]);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Not enough arguments");
                        } catch (LightException e) {
                            System.out.println(e);
                        }

                        break;
                    case "event":
                        try {
                            splitedBySlash = splitedBySpace[1].split("/from ");
                            String[] splitedBySlashTo = splitedBySpace[1].split("/to ");
                            task = new Event(splitedBySlash[0], splitedBySlash[1].substring(0,splitedBySlash[1].indexOf("/to ")).stripTrailing(), splitedBySlashTo[1]);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Not enough arguments");
                        } catch (LightException e) {
                            System.out.println(e);
                        }

                        break;
                }
                if (task!=null) {
                    return new AddCommand(task);
                }

        }
        throw new LightException("Please key in a valid input");
    }
    public static LocalDateTime dateTimeParser(String input, DateTimeFormatter format) {
        //Define the input format
        try {
            // Parse the input string to a LocalDateTime object
            // Return the LocalDateTime object
            return LocalDateTime.parse(input, format);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format! Please use the format: " + format.toString());
            return null;
        }
    }
}
