import java.util.ArrayList;

public class MarkCommand implements Command {

    private final String REGEX = "^mark.*";
    public MarkCommand() {
    };
    @Override
    public void execute(String userInput, ArrayList<Task> listOfTasks) throws NedException{
        String[] words = userInput.split(" ");
        if (words.length != 2) {
            throw new NedException("Sorry m'lord, you must give me a list index with the mark command. No more, no " +
                    "less");
        }
        String possibleIndex = words[1];
        try {
            int index = Integer.parseInt(possibleIndex) - 1;
            Task selectedTask = listOfTasks.get(index);
            selectedTask.markAsDone();
            Ned.print("Good work. One down, more to go!");
            Ned.print(selectedTask.toString());
        } catch (NumberFormatException e) {
            Ned.print("Sorry m'lord, your command must specify a valid number");
            Ned.print(CommandManager.COMMAND_MESSAGE);
        } catch (IndexOutOfBoundsException e) {
            Ned.print("Sorry m'lord, seems the item number you specified is not valid");
            Ned.print(CommandManager.COMMAND_MESSAGE);
        }
    }

    @Override
    public String getRegex() {
        return this.REGEX;
    }
}
