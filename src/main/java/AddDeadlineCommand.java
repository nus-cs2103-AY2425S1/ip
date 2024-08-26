import java.util.ArrayList;

public class AddDeadlineCommand implements Command {
    private final String REGEX = "^deadline.*";
    @Override
    public void execute(String userInput, ArrayList<Task> listOfTasks) throws NedException{
        String[] parsed_inputs = userInput.split("deadline|/by", 3);
        int parsed_inputs_len = Task.checkSizeOfInput(parsed_inputs);
        if (parsed_inputs[1].strip().isBlank()) {
            throw new NedException("M'lord, you cannot create a deadline task with no description");
        } else if (parsed_inputs_len == 1) {
            throw new NedException("M'lord, you cannot create a deadline task with no due date");
        }
        Task newTask = Deadline.createDeadline(parsed_inputs[1].strip(), parsed_inputs[2].strip(), false);
        listOfTasks.add(newTask);
        Ned.print("Aye, I've added this task m'lord:");
        Ned.print(Ned.INDENTATIONS + newTask);
        Ned.print("Now you've " + listOfTasks.size() + " tasks left. Get to it then!");
    }
    @Override
    public String getRegex() {
        return this.REGEX;
    }
}
