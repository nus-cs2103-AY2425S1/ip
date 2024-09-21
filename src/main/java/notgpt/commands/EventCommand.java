package notgpt.commands;

import notgpt.storage.Storage;

public class EventCommand extends Command {

    public static String execute(Storage storage, String text) {
        if (text.isEmpty()) {
            return "bruh? type something to add I'm not adding a blank...";
        }
        try {
            if (!text.contains("/from") || !text.contains("/to")) {
                return "The description of an event must include '/from' and '/to' :/";
            }
            storage.event(text);
            return "Wow " + "\n\"" + storage.getTask(storage.size() - 1) + "\"\n" + "is an event in your life huh?"
                    + String.format(" you have %s tasks now", storage.size());
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}
