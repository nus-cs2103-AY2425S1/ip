package notgpt.commands;

import notgpt.storage.Storage;

public class ClearCommand extends Command {
    public static String execute(Storage storage, String text) {
        storage.clear();
        return "I removed everything from your task list, hope you don't regret it...";
    }
}
