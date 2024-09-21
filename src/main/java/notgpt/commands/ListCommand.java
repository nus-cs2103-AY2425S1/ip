package notgpt.commands;

import notgpt.storage.Storage;

public class ListCommand extends Command {
    public static String execute(Storage storage) {
        if (storage.size() > 0) {
            return storage.toString();
        } else {
            return "it's empty... what do u want me to list? :(";
        }
    }
}
