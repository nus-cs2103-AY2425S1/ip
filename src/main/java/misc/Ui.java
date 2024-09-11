package misc;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Objects;

import exceptions.MissingParamsException;
import exceptions.PositionException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Tasklist;
import task.Todo;

public class Ui {
    /**
     * Prints a farewell message to the user.
     */
    public String endGame() {
        return ("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a start message.
     */
    public String startGame() {
        return ("""
            Hello! I'm Bob \s
            What can I do for you? \s
            """);
    }

    /**
     * Return tasks.
     */
    public String replyGetList(Tasklist tasklist) {
        return ("Here are the tasks in your list:" + "\n"
            + tasklist.getList());
    }

    /**
     * Returns reply for mark done command.
     */
    public String replyMarkDone(int pos, Tasklist tasklist) {
        tasklist.getStr(pos).setDone();
        return ("Nice! I've marked this task as done:" + "\n" + tasklist.getStr(pos));
    }

    /**
     * Returns reply for mark undone command.
     */
    public String replyMarkUndone(int pos, Tasklist tasklist) {
        tasklist.getStr(pos).setUndone();
        return ("OK, I've marked this task as not done yet:" + "\n" + tasklist.getStr(pos));
    }

    /**
     * Returns reply for todo command.
     */
    public String replyTodo(String s, Tasklist tasklist) throws MissingParamsException {
        if (Objects.equals(s, "")) {
            throw new MissingParamsException("todo");
        }

        tasklist.add(new Todo(s));
        return (String.format(" Got it. I've added this task:" + "\n" + tasklist.getLast() + "\n"
                + "Now you have %s tasks in the list", tasklist.getSize()));
    }

    /**
     * Returns reply for event command.
     */
    public String replyEvent(String s, Tasklist tasklist) throws MissingParamsException {
        String[] splitString = s.split("/from|/to", 3);

        // error handling
        if (splitString.length != 3 || Objects.equals(splitString[0].trim(), "")
            || Objects.equals(splitString[1].trim(), "") || Objects.equals(splitString[2].trim(), "")) {
            throw new MissingParamsException("event");
        }

        try {
            LocalDate d1 = LocalDate.parse(splitString[1].trim());
            LocalDate d2 = LocalDate.parse(splitString[2].trim());
            tasklist.add(new Event(splitString[0], d1, d2));
            return (String.format(" Got it. I've added this task:" + "\n" + tasklist.getLast() + "\n"
                + "Now you have %s tasks in the list", tasklist.getSize()));
        } catch (DateTimeException e) {
            return ("Error parsing date: " + e.getMessage()
                + ". Please enter your dates in yyyy-mm-dd format");
        }
    }

    /**
     * Returns reply for deadline command.
     */
    public String replyDeadline(String s, Tasklist tasklist) throws MissingParamsException {
        String[] splitString = s.split("/by");
        if (splitString.length != 2 || Objects.equals(splitString[0], "")
            || Objects.equals(splitString[1], "")) {
                throw new MissingParamsException("deadline");
        }

        try {
            LocalDate d1 = LocalDate.parse(splitString[1].trim());
            tasklist.add(new Deadline(splitString[0], d1));
            return (String.format(" Got it. I've added this task:" + "\n"
                    + tasklist.getStr(tasklist.getSize() - 1) + "\n"
                    + "Now you have %s tasks in the list", tasklist.getSize()));
        } catch (DateTimeException e) {
            return ("Error parsing date: " + e.getMessage()
                + ". Please enter your dates in yyyy-mm-dd format");
        }
    }

    /**
     * Returns reply for delete command.
     */
    public String replyDelete(int pos, Tasklist tasklist) throws PositionException {
        if (pos >= tasklist.getSize() || pos < 0) {
            throw new PositionException(pos);
        }
        Task currTask = tasklist.getStr(pos);
        tasklist.delete(pos);
        return (String.format("Noted. I've removed this task:" + "\n"
                + currTask + "\n"
                + "Now you have %s tasks in the list", tasklist.getSize()));
    }

    /**
     * Return tasks matching regex.
     */
    public String replyFind(String s, Tasklist tasklist) {
        return (String.format("Here are the matching tasks in your list:" + "\n"
                + tasklist.getMatchRegex(s)));
    }
}
