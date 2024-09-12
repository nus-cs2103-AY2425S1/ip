package duke.commands;

import duke.tasks.Storage;

public class TodoCommand extends Command {
    public static String execute(Storage storage, String text) {
        if (text.isEmpty()) {
            return "bruh? type something to add I'm not adding a blank...";
        }
        storage.todo(text);
        return "Added " + '\"' + text + "\"" + " as a new task I guess"
                + String.format(" you have %s tasks now", storage.size());
    }
}

