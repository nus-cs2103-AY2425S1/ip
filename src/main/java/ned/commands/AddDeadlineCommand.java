package ned.commands;

import ned.Storage;
import ned.TaskList;
import ned.exceptions.NedException;

import ned.Ui;

import tasks.Task;

import tasks.Deadline;

public class AddDeadlineCommand implements Command {
    private final String REGEX = "^deadline.*";
    public void execute(TaskList taskList, Ui uiInstance, Storage storageInstance, String userInput) throws NedException {
        String[] parsed_inputs = userInput.split("deadline|/by", 3);
        int parsed_inputs_len = Task.checkSizeOfInput(parsed_inputs);
        if (parsed_inputs[1].strip().isBlank()) {
            throw new NedException("M'lord, you cannot create a deadline task with no description" + uiInstance.getCommandMessage());
        } else if (parsed_inputs_len == 1) {
            throw new NedException("M'lord, you cannot create a deadline task with no due date" + uiInstance.getCommandMessage());
        }
        Task newTask = Deadline.createDeadline(parsed_inputs[1].strip(), parsed_inputs[2].strip(), false);
        taskList.addTask(newTask, uiInstance);
        storageInstance.save(taskList);
    }
    @Override
    public String getRegex() {
        return this.REGEX;
    }
}
