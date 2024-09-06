package mediell;

import java.time.format.DateTimeParseException;
import java.util.Objects;

/** Represents the Ui of the project. */
public class Ui {
    private TaskList tasks;

    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    public String printGreeting() {
        return "Hello! I'm Mediell!\nWhat can I do for you? :)";
    }

    public String printFarewell() {
        return "Bye. Hope to see you again soon! :(";
    }

    public String main(String message) {
        try {
            if (Objects.equals(message, "bye")) {
                return "";
            } else if (Objects.equals(message, "list")) {
                return tasks.displayList();
            } else if (message.startsWith("mark")) {
                int index = Integer.parseInt(message.split(" ", 2)[1]) - 1;
                return tasks.markItem(index);
            } else if (message.startsWith("unmark")) {
                int index = Integer.parseInt(message.split(" ", 2)[1]) - 1;
                return tasks.unmarkItem(index);
            } else if (message.startsWith("delete")) {
                int index = Integer.parseInt(message.split(" ", 2)[1]) - 1;
                return tasks.deleteTask(index);
            } else if (message.startsWith("todo")) {
                String task = message.split(" ", 2)[1];
                return tasks.addToDo(task);
            } else if (message.startsWith("event")) {
                String task = message.split(" ", 2)[1];
                return tasks.addEvent(task);
            } else if (message.startsWith("deadline")) {
                String task = message.split(" ", 2)[1];
                return tasks.addDeadline(task);
            } else if (message.startsWith("find")) {
                String keyword = message.split(" ", 2)[1];
                return tasks.displayFoundList(keyword);
            } else {
                return "Sorry :( I'm confused at what I have to do";
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // if out of range likely because not enough inputs
            return "OOPS!! Not enough inputs were provided";
        } catch (DateTimeParseException e) {
            // if you can't pass date time means incorrect date format
            return "OOPS!! Incorrect date time format provided use YYYY-MM-DD";
        }
    }

    public TaskList getTasks() {
        return tasks;
    }
}
