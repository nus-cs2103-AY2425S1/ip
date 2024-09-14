package darkpool.parser;

import darkpool.task.Deadline;
import darkpool.task.Event;
import darkpool.task.Todo;
import darkpool.util.DarkpoolException;

import java.time.format.DateTimeParseException;
import java.util.Objects;

public class checkCommand {

    static public void findChecker(String[] userInput) throws DarkpoolException {
        if (userInput.length < 2 || Objects.equals(userInput[1], "")) {
            throw new DarkpoolException("you missed the keyword bruh");
        }
    }

    static public int markUnmarkDeleteChecker(String[] userInput) throws DarkpoolException {

        if (userInput.length < 2 || Objects.equals(userInput[1], "")) {
            throw new DarkpoolException("you missed the task number bruh");
        }

        int num;

        try {
            num = Integer.parseInt(userInput[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DarkpoolException("do you know what a number is?");
        }
        return num;
    }

    static public Todo todoChecker(String[] userInput) throws DarkpoolException {
        if (userInput.length < 2 || Objects.equals(userInput[1], "")) {
            throw new DarkpoolException("you missed the task description bruh");
        }
        return new Todo(userInput[1], false);
    }

    static public Deadline deadlineChecker(String[] userInput) throws DarkpoolException {
        if (userInput.length < 2 || Objects.equals(userInput[1], "")) {
            throw new DarkpoolException("wheres the deadline of the task, and whats the task of the deadline??");
        }
        String text = userInput[1];
        if (!text.contains("/by")) {
            throw new DarkpoolException("bruh might as well use todo");
        }
        String[] array = getStrings(text);
        String desc = array[0].trim();
        String by = array[1].trim();
        if (desc.isEmpty()) {
            throw new DarkpoolException("whats the task of the deadline??");
        }
        return new Deadline(desc, by, false);
    }

    static public Event eventChecker(String[] userInput) throws DarkpoolException {
        if (userInput.length < 2 || Objects.equals(userInput[1], "")) {
            throw new DarkpoolException("the event syntax is [task_desc] /from [from_date] /to [to_date]");
        }

        String[] fromParts = getParts(userInput);

        String[] toParts = getToParts(fromParts);
        String desc = fromParts[0];
        String from = toParts[0];
        String to = toParts[1];
        try {
            return new Event(desc, from.trim(), to.trim(), false);
        } catch (DateTimeParseException e) {
            throw new DarkpoolException("bro you know what a date time format is?");
        }
    }

    private static String[] getStrings(String input) throws DarkpoolException {
        if ((input.length() - input.replace("/by", "").length()) / "/by".length() > 1) {
            throw new DarkpoolException("everything good? there cant be multiple deadlines");
        }

        String[] array = input.split("/by");

        if (array.length > 2) {
            throw new DarkpoolException("everything good? there cant be multiple deadlines");
        } else if (array.length == 0) {
            throw new DarkpoolException("wheres the deadline of the task, and whats the task of the deadline??");
        } else if (array.length == 1) {
            throw new DarkpoolException("so close! you forgot to enter the deadline");
        }
        return array;
    }

    private static String[] getToParts(String[] fromParts) throws DarkpoolException {
        String[] toParts = fromParts[1].split("/to ");

        if (toParts.length > 3) {
            throw new DarkpoolException("only one to pls");
        } else if (toParts.length < 2 || toParts[1].trim().isEmpty()) {
            throw new DarkpoolException("to where???");
        } else if (toParts[0].trim().isEmpty()) {
            throw new DarkpoolException("from where???");
        }
        return toParts;
    }

    private static String[] getParts(String[] userInput) throws DarkpoolException {
        String[] fromParts = userInput[1].split("/from");

        if (fromParts.length > 2) {
            throw new DarkpoolException("only one from pls");
        } else if (fromParts.length < 2 || fromParts[1].trim().isEmpty()) {
            throw new DarkpoolException("where is from bruh");
        } else if (fromParts[0].trim().isEmpty()) {
            throw new DarkpoolException("task where???");
        }
        return fromParts;
    }

}
