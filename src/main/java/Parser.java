import java.io.IOException;

public class Parser {
    public static Command parse(String input) throws InputErrorException, IOException {
        if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark ")) {
            int num = Integer.parseInt(input.replaceAll("[^0-9]", ""));
            return new MarkCommand(num);
        } else if (input.startsWith("delete ")) {
            int num = Integer.parseInt(input.replaceAll("[^0-9]", ""));
            return new MarkCommand(num);
        } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
            return new AddCommand(input);
        } else {
            throw(new InputErrorException("Sorry I don't know how to do that"));
        }
    }
}
