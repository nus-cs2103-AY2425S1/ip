package Commands;

import Exceptions.NedException;

import Default.Ui;

import java.util.ArrayList;

import Tasks.Task;

import Tasks.Deadline;

public class AddDeadlineCommand implements Command {
    private final String REGEX = "^deadline.*";
    public void execute(String userInput, ArrayList<Task> listOfTasks) throws NedException {
        String[] parsed_inputs = userInput.split("deadline|/by", 3);
        int parsed_inputs_len = Task.checkSizeOfInput(parsed_inputs);
        if (parsed_inputs[1].strip().isBlank()) {
            throw new NedException("M'lord, you cannot create a deadline task with no description");
        } else if (parsed_inputs_len == 1) {
            throw new NedException("M'lord, you cannot create a deadline task with no due date");
        }
        Task newTask = Deadline.createDeadline(parsed_inputs[1].strip(), parsed_inputs[2].strip(), false);
        listOfTasks.add(newTask);
        Ui.print("Aye, I've added this task m'lord:");
        Ui.print(Ui.INDENTATIONS + newTask);
        Ui.print("Now you've " + listOfTasks.size() + " tasks left. Get to it then!");
    }
    @Override
    public String getRegex() {
        return this.REGEX;
    }
}
