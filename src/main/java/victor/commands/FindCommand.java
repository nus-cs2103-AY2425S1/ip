package victor.commands;

import victor.messages.ReturnMessage;

public class FindCommand extends Command {
    public FindCommand(String[] additionalInput) {
        super(additionalInput);
    }

    @Override
    public ReturnMessage execute() {
        if (taskList.size() == 0) {
            return new ReturnMessage("  ~  No tasks in the list to find a match, add some To Dos, Events,"
                    + " and Deadlines first :)");
        } else {
            if (additionalInput.length == 1) {
                return new ReturnMessage("  ~  Enter a word or phrase to find in your tasks!",
                    "  ~  Use the format find phrase, replacing phrase with what you want to find.");
            } else {
                String toMatch = "";

                for (int i = 1; i < additionalInput.length; i++) {
                    toMatch += additionalInput[i] + " ";
                }

                toMatch = toMatch.trim();
                String[] matches = taskList.findMatches(toMatch);

                if (matches.length == 0) {
                    return new ReturnMessage("  ~  Sorry, there were no tasks matching your phrase!");
                } else {
                    String[] outputMessages = new String[matches.length + 1];
                    outputMessages[0] = "  ~  I found these matches for you!";
                    for (int i = 1; i < outputMessages.length; i++) {
                        outputMessages[i] = matches[i - 1];
                    }
                    return new ReturnMessage(outputMessages);
                }
            }
        }
    }
}
