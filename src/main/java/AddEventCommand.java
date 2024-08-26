import java.util.ArrayList;
import java.util.regex.Pattern;

public class AddEventCommand implements Command {
    private final String REGEX = "^event.*";
    private String EventRegexWithoutTo = "^event (.+) /from (.+)";
    private String EventRegexWithEmptyTo = "^event (.+) /from (.+) /to\\s";
    private String EventRegexWithoutFrom = "^event (.+) /to (.+)";
    private String EventRegexWithEmptyFrom = "^event (.+) /from\\s/to (.+)";

    @Override
    public void execute(String userInput, ArrayList<Task> listOfTasks) throws NedException{
        String[] parsed_inputs = userInput.split("event|/from|/to", 4);
        int parsed_inputs_len = Task.checkSizeOfInput(parsed_inputs);
        if (parsed_inputs[1].strip().isBlank()) {
            throw new NedException("M'lord, you cannot create an event task with no description");
        } else if (parsed_inputs_len <= 2) {
            if (Pattern.matches(EventRegexWithoutTo, userInput) || Pattern.matches(EventRegexWithEmptyTo, userInput)) {
                throw new NedException("M'lord, you cannot create an event task with no 'to' date."
                        + " Gods be good, fill both up!");
            } else if (Pattern.matches(EventRegexWithoutFrom, userInput) || Pattern.matches(EventRegexWithEmptyFrom, userInput)) {
                throw new NedException("M'lord, you cannot create an event task with no 'from' date."
                        + " Gods be good, fill both up!");
            } else {
                throw new NedException("M'lord, you cannot create an event task with no 'from' date " +
                        "or no 'to' date. Gods be good, fill both up!");
            }
        }
        Task newTask = Event.createEvent(parsed_inputs[1].strip(), parsed_inputs[2].strip(), parsed_inputs[3].strip(),
                false);
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
