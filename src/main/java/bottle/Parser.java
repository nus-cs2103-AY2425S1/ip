package bottle;

import bottle.task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Parser {

    public void parseCommand(String input, TaskList taskList, Ui ui) {
        try {
            if (input.equalsIgnoreCase("bye")) {
                ui.printByeMsg();
                System.exit(0);
            } else if (input.equalsIgnoreCase("list")) {
                ui.printTaskList(taskList);
            } else if (input.startsWith("mark ")) {
                markTask(taskList, input.substring(5), ui);
            } else if (input.startsWith("unmark ")) {
                unMarkTask(taskList, input.substring(7), ui);
            } else if (input.startsWith("todo ")) {
                addTodoTask(taskList, input.substring(5), ui);
            } else if (input.startsWith("deadline ")) {
                addDeadlineTask(taskList, input.substring(9), ui);
            } else if (input.startsWith("event ")) {
                addEventTask(taskList, input.substring(6), ui);
            } else {
                throw new RuntimeException("OOPS!!! Something went wrong.");
            }

        } catch (RuntimeException e) {

        }
    }
    public Task parseTask(String input) {
        String[] parts = input.split("\\|");
        Task task;
        try {
            task = switch (parts[0]) {
                case "T " -> new Todo(parts[2]);
                case "D " -> new Deadline(parts[2], parseDateTime(parts[3]));
                case "E " -> new Event(parts[2], parseDateTime(parts[3]), parseDateTime(parts[4]));
                default -> throw new IllegalArgumentException("Wrong input format");
            };
            if (parts[1].equals(" 1 ")) {
                task.mark();
            } else if (parts[1].equals(" 0 ")) {
                task.unMark();
            } else {
                throw new IllegalArgumentException("Wrong isMarked input format");
            }
            return task;
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println("Wrong input format" + e.getMessage());
            return null;
        }
    }

    private static void markTask(TaskList taskList, String taskIndexStr, Ui ui) {
        int taskIndex = Integer.parseInt(taskIndexStr) - 1;
        taskList.markTask(taskIndex);
        ui.printMark(taskList.getTask(taskIndex));
    }

    private static void unMarkTask(TaskList taskList, String taskIndexStr, Ui ui) {
        int taskIndex = Integer.parseInt(taskIndexStr) - 1;
        taskList.unMarkTask(taskIndex);
        ui.printUnMark(taskList.getTask(taskIndex));
    }

    private static void addTodoTask(TaskList taskList, String description, Ui ui) {
        taskList.addTodoTask(description);
        ui.printTaskAddedMessage(taskList.getTaskList());
    }


    private static void addDeadlineTask(TaskList taskList, String input, Ui ui) {
        taskList.addDeadlineTask(input);
        ui.printTaskAddedMessage(taskList.getTaskList());
    }

    private static void addEventTask(TaskList taskList, String input, Ui ui) {
        taskList.addEventTask(input);
        ui.printTaskAddedMessage(taskList.getTaskList());
    }
    private static LocalDateTime parseDateTime(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("OOPS!!! The date format is incorrect. Please use: dd/MM/yyyy HHmm");
        }
    }
}

