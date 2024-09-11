package duke.ui;

import duke.tasks.Storage;

/**
 * The main driver class of the chatbot, called by notGPT
 */
public class Parser {
    private Storage storage = new Storage();
    /**
     * Runs the entire program, reading user input and deciding what to do
     *
     * @param input system input from textbox
     */
    public String parse(String input) {
        String command;
        String text = "";
        String[] parts = input.split("\\s+", 2);
        command = parts[0].toLowerCase();
        if (parts.length > 1 && !parts[1].trim().isEmpty()) {
            text = parts[1];
        }
        switch (command) {
        case "list":
            if (storage.size() > 0) {
                return storage.toString();
            } else {
                return "it's empty... what do u want me to list? :(";
            }
        case "bye":
            return "It's finally over... *yawn*\nI'm heading to bed";
        case "mark":
            int i;
            try {
                i = Integer.valueOf(text);
            } catch (NumberFormatException e) {
                return "sorry bud that ain't a number\ni don't know which task u're referring to...";
            }
            if (0 < i && i <= storage.size()) {
                storage.mark(i);
                return String.format("marked %s as completed\nuse \"list\" to see changes", i);
            } else {
                return "that number isn't a valid task dude..."
                        + "\nit has to be from 1 to " + storage.size();
            }
        case "unmark":
            try {
                i = Integer.valueOf(text);
            } catch (NumberFormatException e) {
                return "sorry bud that ain't a number\ni don't know which task u're referring to...";
            }
            if (0 < i && i <= storage.size()) {
                storage.unmark(i);
                return String.format("marked %s as uncompleted\nuse \"list\" to see changes", i);
            } else {
                return "that number isn't a valid task dude..."
                        + "\nit has to be from 1 to " + storage.size();
            }
        case "todo":
            if (!text.isEmpty()) {
                storage.todo(text);
                return "Added " + '\"' + text + "\"" + " as a new task I guess"
                        + String.format(" you have %s tasks now", storage.size());
            } else {
                return "bruh? type something to add I'm not adding a blank...";
            }
        case "event":
            if (!text.isEmpty()) {
                try {
                    if (text.contains("/from") && text.contains("/to")) {
                        storage.event(text);
                        return "Wow " + '\"' + text + "\"" + " is an event in your life huh?"
                                + String.format(" you have %s tasks now", storage.size());
                    }
                    return "The description of an event must include '/from' and '/to' :/";
                } catch (IllegalArgumentException e) {
                    return e.getMessage();
                }
            } else {
                return "bruh? type something to add I'm not adding a blank...";
            }

        case "deadline":
            if (!text.isEmpty()) {
                try {
                    if (text.contains("/by")) {
                        storage.deadline(text);
                        return "lol " + '\"' + text + "\"" + " is a new deadline, "
                                + "better finish it quick..."
                                + String.format(" you have %s tasks now", storage.size());
                    }
                    return "The description of a deadline must include '/by' :/";
                } catch (IllegalArgumentException e) {
                    return e.getMessage();
                }
            } else {
                return "bruh? type something to add I'm not adding a blank...";
            }
        case "delete":
            try {
                i = Integer.valueOf(text);
            } catch (NumberFormatException e) {
                return "sorry bud that ain't a number\ni don't know which task u're referring to...";
            }
            if (0 < i && i <= storage.size()) {
                storage.delete(i);
                return String.format("deleted %s\nuse \"list\" to see changes", i);
            } else {
                return "that number isn't a valid task dude..."
                        + "\nit has to be from 1 to " + storage.size();
            }
        case "find":
            return storage.find(text);
        case "clear":
            storage.clear();
            return "I removed everything from your task list, hope you don't regret it...";
        default:
            return "what? type an actual command pls...";
        }
    }
}
