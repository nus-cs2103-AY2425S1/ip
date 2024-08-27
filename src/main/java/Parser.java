import java.util.ArrayList;

public class Parser {

    public CommandType commandTypeParser(String input) {
        String commandString = input.split(" ")[0];
        CommandType command = CommandType.fromString(commandString);
        return command;
    }

    public int extractTaskNumber(String input) throws JustbotException {
        String[] splitInputMark = input.split(" ");

        if (splitInputMark.length < 2) {
            throw new JustbotException("Hey man you have provided me an invalid format for mark.\n" +
                    "Use the format: mark [task number]");
        }
        try {
            return Integer.parseInt(splitInputMark[1]);
        } catch (NumberFormatException e) {
            throw new JustbotException("Hey man please input a number for the task number!");
        }
    }


}
