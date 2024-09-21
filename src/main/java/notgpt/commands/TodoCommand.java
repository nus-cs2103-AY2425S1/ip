package notgpt.commands;

import notgpt.storage.Storage;

public class TodoCommand extends Command {
    public static String execute(Storage storage, String text) {
        if (text.isEmpty()) {
            return "bruh? type something to add I'm not adding a blank...";
        }
        storage.todo(text);

        return "Added " + "\n\"" + storage.getTask(storage.size() - 1) + "\"\n" + "as a new task I guess"
                + String.format("\nyou have %s tasks now", storage.size());
    }
}

