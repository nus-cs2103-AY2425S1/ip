package victor.commands;

import victor.messages.ReturnMessage;

/**
 * Find command that extends the Command class, validates input to ensure there are tasks
 * in the task list to search through, and validates that user input a phrase to search for
 * in task names.
 */
public class FindCommand extends Command {
    public FindCommand(String[] additionalInput) {
        super(additionalInput);
    }

    @Override
    public ReturnMessage execute() {
        if (taskList.getSize() == 0) {
            return new ReturnMessage("  ~  No tasks in the list to find a match, add some To Dos, Events,"
                    + " and Deadlines first :)");
        }
        if (additionalInput.length == 1) {
            return new ReturnMessage("  ~  Enter a word or phrase to find in your tasks!",
                "  ~  Use the format find phrase, replacing phrase with what you want to find.");
        }
        String toMatch = getMatchString(additionalInput);
        String[] matches = taskList.findMatches(toMatch);
        // Check if no matches
        if (matches.length == 0) {
            return new ReturnMessage("  ~  Sorry, there were no tasks matching your phrase!");
        }
        // Create array to populate with matching tasks
        String[] outputMessages = new String[matches.length + 1];
        outputMessages[0] = "  ~  I found these matches for you!";
        for (int i = 1; i < outputMessages.length; i++) {
            outputMessages[i] = matches[i - 1];
        }
        return new ReturnMessage(outputMessages);
    }

    /**
     * Extracts the string to match from additional inputs provided to Find command.
     * @param additionalInput A string array containing all additional inputs provided to Find command.
     * @return String to look for in tasks.
     */
    private String getMatchString(String[] additionalInput) {
        String toMatch = "";

        for (int i = 1; i < additionalInput.length; i++) {
            toMatch += additionalInput[i] + " ";
        }

        toMatch = toMatch.trim();
        return toMatch;
    }
}
