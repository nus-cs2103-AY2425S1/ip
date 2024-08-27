package Commands;

import Default.Storage;
import Default.TaskList;
import Exceptions.NedException;

import java.util.ArrayList;

import Tasks.Task;
import Tasks.Event;

import Default.Ui;

import java.util.regex.Pattern;

public class AddEventCommand implements Command {
    private final String REGEX = "^event.*";
    private String EventRegexWithoutTo = "^event (.+) /from (.+)";
    private String EventRegexWithEmptyTo = "^event (.+) /from (.+) /to\\s";
    private String EventRegexWithoutFrom = "^event (.+) /to (.+)";
    private String EventRegexWithEmptyFrom = "^event (.+) /from\\s/to (.+)";

    @Override
    public void execute(TaskList taskList, Ui uiInstance, Storage storageInstance, String userInput) throws NedException {
        String[] parsed_inputs = userInput.split("event|/from|/to", 4);
        int parsed_inputs_len = Task.checkSizeOfInput(parsed_inputs);
        if (parsed_inputs[1].strip().isBlank()) {
            throw new NedException("M'lord, you cannot create an event task with no description" + uiInstance.getCommandMessage());
        } else if (parsed_inputs_len <= 2) {
            if (Pattern.matches(EventRegexWithoutTo, userInput) || Pattern.matches(EventRegexWithEmptyTo, userInput)) {
                throw new NedException("M'lord, you cannot create an event task with no 'to' date."
                        + " Gods be good, fill both up!" + uiInstance.getCommandMessage());
            } else if (Pattern.matches(EventRegexWithoutFrom, userInput) || Pattern.matches(EventRegexWithEmptyFrom, userInput)) {
                throw new NedException("M'lord, you cannot create an event task with no 'from' date."
                        + " Gods be good, fill both up!" + uiInstance.getCommandMessage());
            } else {
                throw new NedException("M'lord, you cannot create an event task with no 'from' date " +
                        "or no 'to' date. Gods be good, fill both up!" + uiInstance.getCommandMessage());
            }
        }
        Task newTask = Event.createEvent(parsed_inputs[1].strip(), parsed_inputs[2].strip(), parsed_inputs[3].strip(),
                false);
        taskList.addTask(newTask, uiInstance);
        storageInstance.save(taskList);
    }
    @Override
    public String getRegex() {
        return this.REGEX;
    }
}
