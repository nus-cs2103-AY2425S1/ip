package Mentos.Commands;

import Mentos.MentosException.MentosException;
import Mentos.components.GuiResponse;
import Mentos.task.TaskList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Command {
    private final String action;
    private final GuiResponse gui = new GuiResponse();

    /**
     * Constructs a Command object with the specified action.
     *
     * @param action The action associated with this command.
     */
    public Command(String action) {
        this.action = action;
    }

    /**
     * Handles regular expression matching for user input.
     *
     * @param input The input string to be matched.
     * @param regex The regular expression pattern to match against.
     * @return A Matcher object if the input matches the pattern, otherwise null.
     */
    public Matcher regexHandler(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher;
        }
        return null;
    }

    /**
     * Returns the GuiResponse object for this command.
     *
     * @return The GuiResponse object.
     */
    public GuiResponse getGui() {
        return this.gui;
    }

    /**
     * Returns the action associated with this command.
     *
     * @return The action string.
     */
    public String getAction() {
        return this.action;
    }

    /**
     * Checks whether the specified index is valid for the given task list.
     *
     * @param index The index to check.
     * @param tasklist The TaskList object to check the index against.
     * @return true if the index is valid.
     * @throws MentosException if the index is out of bounds or invalid.
     */
    public boolean checkIndex(int index, TaskList tasklist) throws MentosException {
        if (index > tasklist.size() || index == 0) {
            throw new MentosException("No Such Tasks!");
        }
        return true;
    }

    /**
     * Executes the command on the given task list.
     * Subclasses must implement this method to define specific command behavior.
     *
     * @param tasklist The TaskList object that the command operates on.
     * @return A string response after execution.
     */
    public abstract String execute(TaskList tasklist);

}
