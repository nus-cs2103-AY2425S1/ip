import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(String userInput) throws ParkException {
        if (userInput.equals("bye")) {
            return new ExitCommand();
        } else if (userInput.equals("list")) {
            return new ListCommand();
        } else if (userInput.startsWith("mark")) {
            try {
                String strIndex = userInput.substring(5);
                int index = Integer.parseInt(strIndex) - 1;
                return new MarkCommand(index);
            } catch (IndexOutOfBoundsException e) {
                throw new ParkException("invalid or missing index");
            }
        } else if (userInput.startsWith("unmark")) {
            try {
                String strIndex = userInput.substring(7);
                int index = Integer.parseInt(strIndex) - 1;
                return new UnmarkCommand(index);
            } catch (IndexOutOfBoundsException e) {
                throw new ParkException("invalid or missing index");
            }
        } else if (userInput.startsWith("delete")) {
            try {
                String strIndex = userInput.substring(7);
                int index = Integer.parseInt(strIndex) - 1;
                return new DeleteCommand(index);
            } catch (IndexOutOfBoundsException e) {
                throw new ParkException("invalid or missing index");
            }
        } else if (userInput.startsWith("todo")) {
            try {
                String desc = userInput.substring(5);
                Task t = new ToDo(desc);
                return new AddCommand(t);
            } catch (IndexOutOfBoundsException e) {
                throw new ParkException("please provide a description");
            }
        } else if (userInput.startsWith("deadline")) {
            try {
                String[] str = userInput.split(" /by ");
                String desc = str[0].substring(9);
                String by = str[1];
                Task t = new Deadline(desc, by);
                return new AddCommand(t);
            } catch (IndexOutOfBoundsException e) {
                throw new ParkException("please use the format: desc /by deadline");
            } catch (DateTimeParseException e) {
                throw new ParkException("please input DateTime in format: yyyy-MM-dd HHmm");
            }
        } else if (userInput.startsWith("event")) {
            try {
                String[] str = userInput.split(" /");
                String desc = str[0].substring(6);
                String start = str[1].substring(5);
                String end = str[2].substring(3);
                Task t = new Event(desc, start, end);
                return new AddCommand(t);
            } catch (IndexOutOfBoundsException e) {
                throw new ParkException("please use the format: desc /from start /to end");
            } catch (DateTimeParseException e) {
                throw new ParkException("please input DateTime in format: yyyy-MM-dd HHmm");
            }
        } else {
            throw new ParkException("invalid input");
        }
    }
}
