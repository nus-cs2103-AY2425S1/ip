package duke.commands;

import duke.tasks.Storage;

public class FindCommand extends Command {
    public static String execute(Storage storage, String text) {
        return storage.find(text);
    }
}
