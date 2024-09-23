package notgpt.commands;

import notgpt.storage.Storage;

public class FindCommand extends Command {
    public static String execute(Storage storage, String text) {
        return storage.find(text);
    }
}
