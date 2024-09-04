package maga;

import maga.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

public class Controller {
    private final Scanner scanner;
    private final TaskList taskList;
    private final Parser parser = new Parser();

    public Controller(Scanner scanner, TaskList taskList) {
        this.scanner = scanner;
        this.taskList = taskList;
    }

    public boolean isExitCommand(String input) {
        if (input.equalsIgnoreCase("bye")) {
            return true;
        }

        return false;
    }

    private String listTasks(Command<Integer> command) {
        return taskList.listTasks();
    }

    private String markTask(Command<Integer> command) {
        return taskList.markTask(command.getContent());
    }

    private String unmarkTask(Command<Integer> command) {
        return taskList.unmarkTask(command.getContent());
    }

    private String deleteTask(Command<Integer> command) {
        return taskList.deleteTask(command.getContent());
    }

    private String findTask(Command<String> command) {
        return taskList.findTask(command.getContent());
    }

    private String createTask(Command<LocalDate[]> command) {
        return taskList.addTask(command);
    }

    public String handleInput(String input) {
        try {
            Command<?> command = parser.handleInput(input);
            switch (command.getCommandType()) {
                case "list" -> {
                    @SuppressWarnings("unchecked")
                    String temp = listTasks((Command<Integer>) command);
                    return temp;
                }
                case "mark" -> {
                    @SuppressWarnings("unchecked")
                    String temp = markTask((Command<Integer>) command);
                    return temp;
                }
                case "unmark" -> {
                    @SuppressWarnings("unchecked")
                    String temp = unmarkTask((Command<Integer>) command);
                    return temp;
                }
                case "delete" -> {
                    @SuppressWarnings("unchecked")
                    String temp = deleteTask((Command<Integer>) command);
                    return temp;
                }
                case "find" -> {
                    @SuppressWarnings("unchecked")
                    String temp = findTask((Command<String>) command);
                    return temp;
                }
                case "todo", "event", "deadline" -> {
                    @SuppressWarnings("unchecked")
                    String temp = createTask((Command<LocalDate[]>) command);
                    return temp;
                }
                default -> throw new InvalidCommandException();
            }
        } catch (DateTimeParseException e) {
            return "Error while parsing date - format in yyyy-MM-dd";
        } catch (NumberFormatException e) {
            return "You can only delete a maga.task number! No one calls amendments by their names!!";
        } catch (InvalidCommandException e) {
            return "HEY! SLEEPY JOE and CROOKED KAMALA " +
                    "might be demented but you're not! Specify a command!";
        }
    }
}
