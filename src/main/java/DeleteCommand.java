import java.util.ArrayList;

public class DeleteCommand implements Command {
    private final String REGEX = "^delete.*";

    public DeleteCommand() {
    }

    @Override
    public void execute(String userInput, ArrayList<Task> listOfTasks) throws NedException{
        String[] words = userInput.split(" ");
        try {
            if (words.length != 2) {
                throw new NedException("Sorry m'lord, you must give me a list index with the delete command. No more, no less");
            } else {
                String possibleIndex = words[1];
                int index = Integer.parseInt(possibleIndex) - 1;
                Ned.print("Noted m'lord. The following task has been removed:");
                Task selectedTask = listOfTasks.get(index); //removes the index specified
                Ned.print(Ned.INDENTATIONS + selectedTask);
                listOfTasks.remove(index);
                Ned.print(String.format("Now you've %d tasks in the list. Get to it then.", listOfTasks.size()));
            }
        } catch (NumberFormatException e) {
            Ned.print("Sorry m'lord, your command must specify a valid number");
            Ned.print(CommandManager.COMMAND_MESSAGE);
        }
    }

    @Override
    public String getRegex() {
        return this.REGEX;
    }
}
