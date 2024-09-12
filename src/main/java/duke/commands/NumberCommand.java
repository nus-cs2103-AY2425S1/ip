package duke.commands;

import duke.tasks.Storage;
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
                storage.delete(i);
                return String.format("deleted %s\nuse \"list\" to see changes", i);
            case "mark":
                storage.mark(i);
                return String.format("marked %s as completed\nuse \"list\" to see changes", i);
            case "unmark":
                storage.unmark(i);
                return String.format("marked %s as uncompleted\nuse \"list\" to see changes", i);
            default:
                return "I cant really use this command with numbers doe...";
            }
        } else {
            return "that number isn't a valid task dude..."
                    + "\nit has to be from 1 to " + storage.size();
        }
    }
}
