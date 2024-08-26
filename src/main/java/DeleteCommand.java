import java.util.ArrayList;

public class DeleteCommand {
    private final String commandInput;

    public DeleteCommand(String commandInput) {
        this.commandInput = commandInput;
    }

    public int parseDeleteCommand() throws NedException{
        String[] words = this.commandInput.split(" ");
        try {
            if (words.length != 2) {
                throw new NedException("Sorry m'lord, you must give me a list index with the delete command. No more, no less");
            } else {
                String possibleIndex = words[1];
                int index = Integer.parseInt(possibleIndex) - 1;
                return index;
            }
        } catch (NumberFormatException e) {
            Ned.print("Sorry m'lord, your command must specify a valid number");
            Ned.print(CommandManager.COMMAND_MESSAGE);
        }
        return -1;
    }
}
