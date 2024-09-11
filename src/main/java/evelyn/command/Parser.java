package evelyn.command;

import java.time.DateTimeException;
import java.util.Objects;

import evelyn.exception.InvalidInputException;
import evelyn.exception.NoInputException;
import evelyn.task.Deadline;
import evelyn.task.Event;
import evelyn.task.Todo;

/**
 * Houses all the logic for parsing words that have been inputted
 */
public class Parser {
    private TaskList lst;
    public Parser(TaskList taskList) {
        this.lst = taskList;
    }

    /**
     * Method parses the input string to determine what command is required.
     * @param text The input text
     * @return The response text
     * @throws NoInputException Thrown when no input has been detected after a keyword
     * @throws DateTimeException Thrown when the wrong date format has been keyed in
     */
    public String parse(String text) throws NoInputException, DateTimeException {
        assert text != null : "Input text must be a string";
        if ((Objects.equals(text, "bye")) || (Objects.equals(text, "BYE")) || (Objects.equals(text, "Bye"))) {
            this.lst.saveTasks();
            return "Bye Bye!!";
        } else if (text.startsWith("delete")) {
            int index = Integer.parseInt(text.substring(7)) - 1;
            return lst.removeTask(index);
        } else if (Objects.equals(text, "list")) {
            return lst.listTask();
        } else if (text.startsWith("mark")) {
            int index = Integer.parseInt(text.substring(5)) - 1;
            return lst.markTask(index);
        } else if (text.startsWith("unmark")) {
            int index = Integer.parseInt(text.substring(7)) - 1;
            return lst.unmarkTask(index);
        } else if (text.startsWith("find")) {
            if (text.length() <= 5 || Character.isWhitespace(text.charAt(5))) {
                throw new InvalidInputException("Invalid input for find command");
            }
            String keyword = text.substring(5);
            return lst.find(keyword);
        } else if (text.startsWith("todo")) {
            if (text.length() <= 5 || Character.isWhitespace(text.charAt(5))) {
                throw new InvalidInputException("Invalid input for todo");
            }
            String description = text.substring(5);
            Todo newTodo = new Todo(description, false);
            return lst.addTask(newTodo);
        } else if (text.startsWith("deadline")) {
            if (text.length() <= 9 || Character.isWhitespace(text.charAt(9))) {
                throw new InvalidInputException("invalid input for deadline");
            }
            String input = text.substring(9);
            String[] parts = input.split(" /by ");
            String description = parts[0];
            String deadline = parts[1];
            Deadline newDeadline = new Deadline(description, deadline, false);
            return lst.addTask(newDeadline);
        } else if (text.startsWith("event")) {
            if (text.length() <= 6 || Character.isWhitespace(text.charAt(6))) {
                throw new InvalidInputException("invalid input for event");
            }
            String input = text.substring(6);
            String[] descriptionAndDates = input.split(" /from ");
            String description = descriptionAndDates[0];
            String[] startAndEndDates = descriptionAndDates[1].split(" /to ");
            String start = startAndEndDates[0];
            String end = startAndEndDates[1];
            Event newEvent = new Event(description, start, end, false);
            return lst.addTask(newEvent);
        } else {
            throw new NoInputException("no input!");
        }
    }
}
