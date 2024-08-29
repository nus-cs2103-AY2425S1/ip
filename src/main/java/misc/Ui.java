package misc;
import java.util.Objects;
import java.time.*;
import exceptions.MissingParamsException;
import exceptions.PositionException;
import task.*;

public class Ui {
    String blankline = "____________________________________________________________ \s";
    int markPos;
    Tasklist tasklist;

    public Ui(Tasklist tasklist) {
        this.tasklist = tasklist;
    }
    
    public void endGame() {
        System.out.println(blankline + "\n" + "Bye. Hope to see you again soon!");
    }

    public void startGame() {
        System.out.println(  """
            ____________________________________________________________ \s
            Hello! I'm Bob \s
            What can I do for you? \s
            ____________________________________________________________ \s
            """);
    }

    public String retBlank() {
        return blankline;
    }

    public void replyGetList(Tasklist tasklist) {
        System.out.println("Here are the tasks in your list:" + "\n"
        + tasklist.getList() + blankline);
    }

    public Tasklist replyMarkDone(int pos, Tasklist tasklist) {
        tasklist.getStr(pos).setDone();
        System.out.println("Nice! I've marked this task as done:" + "\n"
                + tasklist.getStr(pos) + "\n" + blankline);
        return tasklist;
    }

    public Tasklist replyMarkUndone(int pos, Tasklist tasklist) {
        tasklist.getStr(pos).setUndone();
        System.out.println("OK, I've marked this task as not done yet:" + "\n"
                + tasklist.getStr(pos) + "\n" + blankline);
        return tasklist;
    }

    public Tasklist replyTodo(String s, Tasklist tasklist) throws MissingParamsException {
        if (Objects.equals(s, "")) throw new MissingParamsException("todo");
        tasklist.add(new Todo(s));
        System.out.println(String.format(" Got it. I've added this task:" + "\n"
                + tasklist.getLast() + "\n"
                + "Now you have %s tasks in the list", tasklist.getSize()) + "\n" + blankline);
        return tasklist;
    }

    public Tasklist replyEvent(String s, Tasklist tasklist) throws MissingParamsException {
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
            System.out.println(String.format(" Got it. I've added this task:" + "\n"
            + tasklist.getLast()+ "\n"
            + "Now you have %s tasks in the list", tasklist.getSize()) + "\n" + blankline);
        } catch (DateTimeException e) {
            System.out.println("Error parsing date: " + e.getMessage() + ". Please enter your dates in yyyy-mm-dd format" + blankline);
        }
        return tasklist;
    }

    public Tasklist replyDeadline(String s, Tasklist tasklist) throws MissingParamsException {
        String[] splitString = s.split("/by");
        if (splitString.length != 2 || Objects.equals(splitString[0], "") || Objects.equals(splitString[1], "")) {
            throw new MissingParamsException("deadline");
        }

        try {
            LocalDate d1 = LocalDate.parse(splitString[1].trim());
            tasklist.add(new Deadline(splitString[0], d1));
            System.out.println(String.format(" Got it. I've added this task:" + "\n"
                    + tasklist.getStr(tasklist.getSize() - 1) + "\n"
                    + "Now you have %s tasks in the list", tasklist.getSize()) + "\n" + blankline);
        } catch (DateTimeException e) {
            System.out.println("Error parsing date: " + e.getMessage() + ". Please enter your dates in yyyy-mm-dd format");
        }
        return tasklist;
    }

    public Tasklist replyDelete(int pos, Tasklist tasklist) throws PositionException{
        if (pos >= tasklist.getSize() || markPos < 0) {
            throw new PositionException(markPos);
        }
        Task currTask = tasklist.getStr(markPos);
        tasklist.delete(markPos);
        System.out.println(String.format("Noted. I've removed this task:" + "\n"
                + currTask + "\n"
                + "Now you have %s tasks in the list", tasklist.getSize()) + "\n" + blankline);
        return tasklist;
    }

}
