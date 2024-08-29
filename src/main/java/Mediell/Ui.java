package Mediell;

import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Ui {
    private TaskList tasks;

    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    private static void printLine() {
        System.out.println("-------------------------------------------");
    }

    public void printGreeting() {
        printLine();
        System.out.println("Hello! I'm Mediell!");
        System.out.println("What can I do for you? :)");
        printLine();
    }

    public void printFarewell() {
        System.out.println("Bye. Hope to see you again soon! :(");
        printLine();
    }

    public boolean main(String message) {
        try {
            if (Objects.equals(message, "bye")) {
                return false;
            } else if (Objects.equals(message, "list")) {
                tasks.displayList();
            } else if (message.startsWith("mark")) {
                int index = Integer.parseInt(message.split(" ", 2)[1]) - 1;
                tasks.markItem(index);
            } else if (message.startsWith("unmark")) {
                int index = Integer.parseInt(message.split(" ", 2)[1]) - 1;
                tasks.unMarkItem(index);
            } else if (message.startsWith("delete")) {
                int index = Integer.parseInt(message.split(" ", 2)[1]) - 1;
                tasks.deleteTask(index);
            } else if (message.startsWith("todo")) {
                String task = message.split(" ", 2)[1];
                tasks.addToDo(task);
            } else if (message.startsWith("event")) {
                String task = message.split(" ", 2)[1];
                tasks.addEvent(task);
            } else if (message.startsWith("deadline")) {
                String task = message.split(" ", 2)[1];
                tasks.addDeadline(task);
            } else if (message.startsWith("find")) {
                String keyword = message.split(" ", 2)[1];
                tasks.displayFoundList(keyword);
            } else {
                System.out.println("Sorry :( I'm confused at what I have to do");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // if out of range likely because not enough inputs
            System.out.println("OOPS!! Not enough inputs were provided");
        } catch (DateTimeParseException e) {
            // if you can't pass date time means incorrect date format
            System.out.println("OOPS!! Incorrect date time format provided use YYYY-MM-DD");
        }
        printLine();
        return true;
    }

    public TaskList getTasks() {
        return tasks;
    }
}
