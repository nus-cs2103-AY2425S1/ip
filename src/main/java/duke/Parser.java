package duke;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

public class Parser {

    public static Command ParseCommand(String userInput) {
        if (userInput.contains("bye")) {
            return new ExitCommand(userInput);
        } else if (userInput.contains("list")) {
            return new ListCommand(userInput);
        } else if (userInput.contains("unmark")) {
            return new UnMarkCommand(userInput);
        } else if (userInput.contains("mark")) {
            return new MarkCommand(userInput);
        } else if (userInput.contains("todo")) {
            return new AddTodoCommand(userInput);
        } else if (userInput.contains("deadline")) {
            return new AddDeadLineCommand(userInput);
        } else if (userInput.contains("event")) {
            return new AddEventCommand(userInput);
        } else if (userInput.contains("delete")) {
            return new DeleteCommand(userInput);
        } else if (userInput.contains("find")) {
            return new FindCommand(userInput);
        } else if (userInput.contains("sort")) {
            return new SortCommand(userInput);
        } else {
            System.out.println("Command does not exist!");
        }
        return null;
    }

    public static String[] ParseString(String input, ArrayList<String> splitters) {
        return TrimArray(ParseStringHelper(input, splitters));
    }

    private static String[] ParseStringHelper(String input, ArrayList<String> splitters) {
        String[] split = input.split(splitters.get(0));
        if (split.length == 0) {
            return new String[]{};
        }
        if (splitters.size() == 1) {
            return split;
        } else {
            splitters.remove(0);
            return Stream
                    .of(new String[]{split[0].trim()},
                            split.length == 2 ? ParseString(split[1], splitters) : new String[]{}) // Stream<String[]>
                    .flatMap(x -> Stream.of(x)) // flattens to Stream<String>
                    .toArray(String[]::new);
        }
    }


    /**
     * Returns LocalDatetime
     *
     * @param dateString
     */
    public static LocalDateTime parseDate(String dateString) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy HHmm", Locale.ENGLISH);
        Date date = format.parse(dateString);
        return new java.sql.Timestamp(date.getTime()).toLocalDateTime();
    }

    /**
     * Returns index from string
     *
     * @param input
     * @param commands
     */
    public static int ParseIndex(String input, ArrayList<String> commands) throws DukeException {
        String[] markItems = TrimArray(ParseString(input, commands));

        if (markItems.length == 0 || Objects.equals(markItems[1], "")) {
            throw new DukeException("Number must be specified!");
        }
        Integer num;
        try {
            num = Integer.parseInt(markItems[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Number must be specified!");
        }
        return num;
    }

    public static String[] TrimArray(String[] strArray) {
        return Arrays.stream(strArray).map(String::trim).toArray(String[]::new);
    }
}
