package duke.commands;

import duke.tasks.Storage;
public class DeadlineCommand extends Command {
    public static String execute(Storage storage, String text) {
        if (text.isEmpty()) {
            return "bruh? type something to add I'm not adding a blank...";
        }
        try {
            if (!text.contains("/by")) {
                return "The description of a deadline must include '/by' :/";
            }
            storage.deadline(text);
            return "lol " + '\"' + text + "\"" + " is a new deadline, "
                    + "better finish it quick..."
                    + String.format(" you have %s tasks now", storage.size());
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}
