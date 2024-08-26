import java.util.ArrayList;

public class MarkAndUnmarkCommand {


    public MarkAndUnmarkCommand() {
    };
    public int parseMarkOrUnmarkCommand(String input) throws NedException{
        String[] words = input.split(" ");
        if (words.length != 2) {
            throw new NedException("Sorry m'lord, you must give me a list index with the mark/unmark command. No more, no less");
        }
        String possibleIndex = words[1];
        try {
            int index = Integer.parseInt(possibleIndex) - 1;
            return index;
        } catch (NumberFormatException e) {
            Ned.print("Sorry m'lord, your command must specify a valid number");
            Ned.print(CommandManager.COMMAND_MESSAGE);
        }
        return -1;
    }
};
