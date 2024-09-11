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
    private TaskList list;
    public Parser(TaskList taskList) {
        this.list = taskList;
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
            this.list.saveTasks();
            return "Bye Bye!!";
        } else if (text.startsWith("delete")) {
            int index = Integer.parseInt(text.substring(7)) - 1;
            return list.removeTask(index);
        } else if (text.startsWith("del")  && Character.isDigit(text.charAt(4))) {
            int index = Integer.parseInt(text.substring(4)) - 1;
            return list.removeTask(index);
        } else if (Objects.equals(text, "list")) {
            return list.listTask();
        } else if (text.startsWith("mark")) {
            int index = Integer.parseInt(text.substring(5)) - 1;
            return list.markTask(index);
        } else if (text.startsWith("m") && Character.isDigit(text.charAt(2))) {
            int index = Integer.parseInt(text.substring(2)) - 1;
            return list.markTask(index);
        } else if (text.startsWith("unmark")) {
            int index = Integer.parseInt(text.substring(7)) - 1;
            return list.unmarkTask(index);
        } else if (text.startsWith("um")  && Character.isDigit(text.charAt(3))) {
            int index = Integer.parseInt(text.substring(3)) - 1;
            return list.unmarkTask(index);
        } else if (text.startsWith("find")) {
            if (text.length() <= 5 || Character.isWhitespace(text.charAt(5))) {
                throw new InvalidInputException("Invalid input for find command");
            }
            String keyword = text.substring(5);
            return list.find(keyword);
        } else if (text.startsWith("todo")) {
            if (text.length() <= 5 || Character.isWhitespace(text.charAt(5))) {
                throw new InvalidInputException("Invalid input for todo");
            }
            String[] taskAndDescription = text.split(" ");
            Todo newTodo = new Todo(taskAndDescription[1], false);
            return list.addTask(newTodo);
        } else if (text.startsWith("deadline")) {
            if (text.length() <= 9 || Character.isWhitespace(text.charAt(9))) {
                throw new InvalidInputException("invalid input for deadline");
            }
            String input = text.substring(9);
            String[] descriptionAndDate = input.split(" /by ");
            String description = descriptionAndDate[0];
            String date = descriptionAndDate[1];
            Deadline newDeadline = new Deadline(description, date, false);
            return list.addTask(newDeadline);
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
            return list.addTask(newEvent);
        } else if (text.startsWith("t")) {
            if (Character.isWhitespace(text.charAt(2))) {
                throw new InvalidInputException("invalid input for todo");
            }
            String[] taskAndDescription = text.split(" ");
            Todo newTodo = new Todo(taskAndDescription[1], false);
            return list.addTask(newTodo);
        } else if (text.startsWith("d")) {
            if (Character.isWhitespace(text.charAt(2))) {
                throw new InvalidInputException("invalid input for deadline");
            }
            String input = text.substring(2);
            String[] descriptionAndDate = input.split(" /by ");
            String description = descriptionAndDate[0];
            String date = descriptionAndDate[1];
            Deadline newDeadline = new Deadline(description, date, false);
            return list.addTask(newDeadline);
        } else if (text.startsWith("e")) {
            if (Character.isWhitespace(text.charAt(2))) {
                throw new InvalidInputException("invalid input for deadline");
            }
            String input = text.substring(6);
            String[] descriptionAndDates = input.split(" /from ");
            String description = descriptionAndDates[0];
            String[] startAndEndDates = descriptionAndDates[1].split(" /to ");
            String start = startAndEndDates[0];
            String end = startAndEndDates[1];
            Event newEvent = new Event(description, start, end, false);
            return list.addTask(newEvent);
        } else {
            throw new NoInputException("no input!");
        }
    }
}
