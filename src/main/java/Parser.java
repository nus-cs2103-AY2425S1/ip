import java.time.LocalDate;
import java.time.format.DateTimeParseException;
public class Parser {
    public static Command parse(String input) throws BimoException{
        String[] parsedArray = input.split(" ");
        String cmd = parsedArray[0].toLowerCase();
        if (cmd.equals("list")) {
            return new ListCommand();

        } else if (cmd.equals("mark")) {
            int index = Integer.valueOf(parsedArray[1]) - 1;
            return new MarkCommand(index);

        } else if (cmd.equals("unmark")) {
            int index = Integer.valueOf(parsedArray[1]) - 1;
            return new UnmarkCommand(index);

        } else if (cmd.equals("todo")) {
            String description = parseDescription(input);
            return new AddCommand(new ToDo(description));

        } else if (cmd.equals("event")) {
            String[] array = input.split("/from ");
            String description = parseDescription(array[0]);
            String start = parseDate(false, false, array);
            String end = parseDate(false, true, array);
            LocalDate startDate = convertDate(start);
            LocalDate endDate = convertDate(end);
            Task task = new Event(description, startDate,
                   endDate);
            return new AddCommand(task);

        } else if (cmd.equals("deadline")) {
            String[] array = input.split("/by ");
            String description = parseDescription(array[0]);
            String dueDate = parseDate(true, true, array);
            LocalDate ld = convertDate(dueDate);
            Task task = new Deadline(description, ld);
            return new AddCommand(task);

        } else if (cmd.equals("delete")) {
            int index = Integer.valueOf(parsedArray[1]) - 1;
            return new DeleteCommand(index);
        } else if (cmd.equals("bye")){
            return new ByeCommand();
        } else {
            throw new BimoException("    Sorry, I do not understand you \n" +
                    "    as this is not a valid command");
        }
    }

    public static String parseDescription(String input) throws BimoException{
        String[] parsedArray = input.split(" ");
        if (parsedArray.length <= 1) {
            throw new BimoException("    Please key in description for your task");
        }
        parsedArray[0] = "";
        return removeSpace(parsedArray);
    }
    public static String removeSpace(String[] input) {
        String [] temp = new String[input.length - 1];
        for (int i = 1; i < input.length; i++) {
            temp[i - 1] = input[i];
        }
        return String.join(" ", temp);
    }
    public static String parseDate(boolean isDeadline, boolean isEnd,
            String[] array) throws BimoException {
        if (array.length <= 1) {
            String type = isDeadline ? " /by" : " /from .... /to";
            throw new BimoException("    Please provide a date using" + type);
        } else if (!isDeadline) {
            array = array[1].split(" /to ");
            if (array.length <= 1) {
                throw new BimoException("    Please provide a deadline using /to");
            }
        }
        return isEnd ? array[1] : array[0];
    }

    public static LocalDate convertDate(String date) throws BimoException {
        try {
            LocalDate ld = LocalDate.parse(date);
            return ld;
        } catch (DateTimeParseException e) {
            throw new BimoException("Unable to parse date, please use yyyy-mm-dd");
        }
    }
}
