package bruno;

import bruno.command.*;
import bruno.exceptions.BrunoException;
import bruno.exceptions.UnknownCommandException;
import bruno.task.TaskList;

public class Parser {
    public static Command parse(String command, TaskList tasks) {
        String firstWord;
        String restOfString = "";
        command = command.trim();

        if (command.contains(" ")) {
            firstWord = command.substring(0, command.indexOf(" "));
            restOfString = command.split(" ", 2)[1];
        } else {
            firstWord = command;
        }

        try {
            if (command.equalsIgnoreCase("bye")) {
                return new ExitCommand(tasks);
            } else if (command.equalsIgnoreCase("list")) {
                return new ListCommand(tasks);
            } else if (firstWord.equalsIgnoreCase("mark")) {
                return new MarkCommand(tasks, restOfString);
            } else if (firstWord.equalsIgnoreCase("unmark")) {
                return new UnmarkCommand(tasks, restOfString);
            } else if (firstWord.equalsIgnoreCase("delete")) {
                return new DeleteCommand(tasks, restOfString);
            } else if (firstWord.equalsIgnoreCase("todo")) {
                return new AddCommand(tasks, restOfString, Bruno.TaskType.TODO);
            } else if (firstWord.equalsIgnoreCase("deadline")) {
                return new AddCommand(tasks, restOfString, Bruno.TaskType.DEADLINE);
            } else if (firstWord.equalsIgnoreCase("event")) {
                return new AddCommand(tasks, restOfString, Bruno.TaskType.EVENT);
            } else if (firstWord.equalsIgnoreCase("find")) {
                return new FindCommand(tasks, restOfString);
            } else {
                throw new UnknownCommandException();
            }
        } catch (BrunoException e) {
            Ui.printErrorMessage(e);
            return null;
        }
    }
}
