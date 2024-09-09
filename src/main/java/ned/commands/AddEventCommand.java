package ned.commands;

import java.util.regex.Pattern;

import ned.Storage;
import ned.TaskList;
import ned.exceptions.MissingTaskDescriptionException;
import ned.exceptions.MissingTaskFromDateException;
import ned.exceptions.MissingTaskToDateException;
import ned.exceptions.NedException;
import ned.Ui;
import ned.tasks.Event;
import ned.tasks.Task;

/**
 * Represents the event command, which when executed, adds a new event to the list of tasks.
 */
public class AddEventCommand implements Command {
    private final String REGEX = "^event.*";
    private String eventRegexWithoutTo = "^event (.+) /from (.+)";
    private String eventRegexWithEmptyTo = "^event (.+) /from (.+) /to\\s";
    private String eventRegexWithoutFrom = "^event (.+) /to (.+)";
    private String eventRegexWithEmptyFrom = "^event (.+) /from\\s/to (.+)";

    /**
     * This method creates a new Event object and then adds it to the taskList instance belonging to the Ned instance.
     *
     * It will throw NedException if the command is incomplete, be it missing a description, a /from timing or a
     * /to timing.
     *
     * @param taskList        The TaskList instance associated with the Ned instance, used to store the list of tasks
     *                        and to modify them
     * @param uiInstance      The Ui instance associated with the Ned instance, used to display user output, and take in
     *                        user input
     * @param storageInstance The storage instance associated with the Ned instance, used to load in saved tasks
     *                        from the cache file as well as to modify the cache file when the list of tasks are changed
     * @param userInput       The string of user input, is parsed for relevant information needed to execute commands
     * @throws NedException A generic exception associated with the Ned class. It is caught and its message shown
     *                      to the user
     */
    @Override
    public void execute(TaskList taskList, Ui uiInstance, Storage storageInstance, String userInput)
            throws NedException {
        String[] parsed_inputs = userInput.split("event|/from|/to", 4);
        int parsed_inputs_len = Task.checkSizeOfInput(parsed_inputs);
        if (parsed_inputs[1].strip().isBlank()) {
            throw new MissingTaskDescriptionException("M'lord, you cannot create an event task with no description"
                    + uiInstance.getCommandMessage());
        } else if (parsed_inputs_len <= 2) {
            if (Pattern.matches(eventRegexWithoutTo, userInput) || Pattern.matches(eventRegexWithEmptyTo, userInput)) {
                throw new MissingTaskToDateException("M'lord, you cannot create an event task with no 'to' date."
                        + " Gods be good, fill both up!" + uiInstance.getCommandMessage());
            } else if (Pattern.matches(eventRegexWithoutFrom, userInput)
                    || Pattern.matches(eventRegexWithEmptyFrom, userInput)) {
                throw new MissingTaskFromDateException("M'lord, you cannot create an event task with no 'from' date."
                        + " Gods be good, fill both up!" + uiInstance.getCommandMessage());
            } else {
                throw new MissingTaskToDateException("M'lord, you cannot create an event task with no 'from' date " +
                        "or no 'to' date. Gods be good, fill both up!" + uiInstance.getCommandMessage());
            }
        }
        Task newTask = Event.createEvent(parsed_inputs[1].strip(), parsed_inputs[2].strip(), parsed_inputs[3].strip(),
                false);
        taskList.addTask(newTask, uiInstance);
        storageInstance.save(taskList);
    }

    /**
     * Returns the regex expression used to identify the event command.
     *
     * Used in Parser class to find the associated command.
     *
     * @return The regex pattern associated with this command
     */
    @Override
    public String getRegex() {
        return this.REGEX;
    }
}
