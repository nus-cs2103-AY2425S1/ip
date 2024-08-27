package Commands;

import Default.TaskList;
import Exceptions.NedException;

import Default.Ui;

import java.util.ArrayList;

import Tasks.Task;

import Tasks.Deadline;

public class AddDeadlineCommand implements Command {
    private final String REGEX = "^deadline.*";
    public void execute(String userInput, TaskList taskList) throws NedException {
        String[] parsed_inputs = userInput.split("deadline|/by", 3);
        int parsed_inputs_len = Task.checkSizeOfInput(parsed_inputs);
        if (parsed_inputs[1].strip().isBlank()) {
            throw new NedException("M'lord, you cannot create a deadline task with no description");
        } else if (parsed_inputs_len == 1) {
            throw new NedException("M'lord, you cannot create a deadline task with no due date");
        }
        Task newTask = Deadline.createDeadline(parsed_inputs[1].strip(), parsed_inputs[2].strip(), false);
        taskList.addTask(newTask);
    }
    @Override
    public String getRegex() {
        return this.REGEX;
    }
}
