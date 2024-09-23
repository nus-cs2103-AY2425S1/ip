package notgpt.commands;

import notgpt.storage.Storage;
public class NumberCommand extends Command {
    public static String execute(Storage storage, String text, String command) {
        int i;
        try {
            i = Integer.valueOf(text);
        } catch (NumberFormatException e) {
            return "sorry bud that ain't a number\ni don't know which task u're referring to...";
        }
        if (0 < i && i <= storage.size()) {
            switch (command) {
            case "delete":
                String task = storage.getTask(i - 1);
                storage.delete(i);
                return String.format("deleted %s\n\"%s\"\nHope you got that right...", i, task);
            case "mark":
                storage.mark(i);
                task = storage.getTask(i - 1);
                return String.format("marked %s as completed\n\"%s\"\nwow you actually did something", i, task)
                        + "... amazing...";
            case "unmark":
                storage.unmark(i);
                task = storage.getTask(i - 1);
                return String.format("marked %s as uncompleted\n\"%s\"\nso finishing that was a lie huh?", i, task)
                        + " Same tbh... doing stuff is hard";
            default:
                return "I cant really use this command with numbers doe...";
            }
        } else {
            return "that number isn't a valid task dude..."
                    + "\nit has to be from 1 to " + storage.size();
        }
    }
}
