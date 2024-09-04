package maga;

import com.sun.source.tree.TryTree;
import maga.task.DeadlineTask;
import maga.task.EventTask;
import maga.task.TaskList;
import maga.task.TodoTask;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

public class Parser {

    public Parser() {
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private Command<Integer> listTasks() {
        return new Command<>("list", null);
    }

    private Command<Integer> markTask(String input) {
        char[] charArray = input.toCharArray();
        int taskNumber = Character.getNumericValue(charArray[charArray.length - 1]) - 1;
        return new Command<>("mark", taskNumber);
    }

    private Command<Integer> unmarkTask(String input) {
        char[] charArray = input.toCharArray();
        int taskNumber = Character.getNumericValue(charArray[charArray.length - 1]) - 1;
        return new Command<>("unmark", taskNumber);
    }

    private Command<Integer> deleteTask(String input) {
        String descrip = input.substring(7).trim();
        int taskNumber = Integer.parseInt(descrip) - 1;
        return new Command<>("delete", taskNumber);
    }

    private Command<LocalDate[]> addTodoTask(String input) {
        String description = input.substring(5).trim();
        return new Command<>("todo", description, null);
    }

    private Command<LocalDate[]> addEventTask(String input) throws DateTimeParseException {
        String description = input.substring(6).trim();
        String[] descriptionArray = description.split("/");
        LocalDate[] date = new LocalDate[1];
        try {
            date[0] = LocalDate.parse(descriptionArray[1], formatter);
        } catch (DateTimeParseException e) {
            throw e;
        }
        return new Command<>("event", descriptionArray[0], date);
    }

    private Command<LocalDate[]> addDeadlineTask(String input) throws DateTimeParseException {
        String description = input.substring(9).trim();
        String[] descriptionArray = description.split("/");
        LocalDate fromDate, toDate;
        try {
            fromDate = LocalDate.parse(descriptionArray[1], formatter);
            toDate = LocalDate.parse(descriptionArray[2], formatter);
        } catch (DateTimeParseException e) {
            throw e;
        }

        LocalDate[] dateArray = {fromDate, toDate};
        return new Command<>("deadline", descriptionArray[0], dateArray);
    }

    public Command<?> handleInput(String input) throws InvalidCommandException, DateTimeParseException {
        input = input.toLowerCase();
        String command = input.split(" ")[0];

        return switch (command) {
            case "list" -> listTasks();
            case "mark" -> markTask(input);
            case "unmark" -> unmarkTask(input);
            case "delete" -> deleteTask(input);
            case "todo" -> addTodoTask(input);
            case "event" -> addEventTask(input);
            case "deadline" -> addDeadlineTask(input);
            default -> throw new InvalidCommandException();
        };
    }
}
