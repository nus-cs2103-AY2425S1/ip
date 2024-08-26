package chatbot;

import chatbot.exception.*;
import chatbot.task.Deadline;
import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.Todo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Task parseFileLine(String taskString) throws IOException {
        // TODO error handling for if data file is corrupted
        String[] tokens = taskString.split("\\|");
        boolean isDone = Integer.parseInt(tokens[1]) == 1;
        String title = tokens[2];
        return switch (tokens[0]) {
            case "T" -> new Todo(title, isDone);
            case "D" -> new Deadline(title, LocalDateTime.parse(tokens[3]), isDone);
            case "E" -> new Event(title, LocalDateTime.parse(tokens[3]), LocalDateTime.parse(tokens[4]), isDone);
            default -> throw new IOException();
        };
    }

    public static void processInput(String s, TaskList taskList, Storage storage, Ui ui) throws InputException {
        String[] inputArr = s.split(" ", 2);
        String command = inputArr[0];
        switch (command) {
        case "bye" -> {
            ui.endRun();
            System.out.println("Bye. Hope to see you again soon!");
        }
        case "list" -> {
            taskList.listTasks();
        }
        case "mark" -> {
            if (inputArr.length == 1 || inputArr[1].trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            int idx = Integer.parseInt(inputArr[1]) - 1;
            taskList.mark(idx, true);
            storage.writeToFile(taskList);
        }
        case "unmark" -> {
            if (inputArr.length == 1 || inputArr[1].trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            int idx = Integer.parseInt(inputArr[1]) - 1;
            taskList.mark(idx, false);
            storage.writeToFile(taskList);
        }
        case "delete" -> {
            if (inputArr.length == 1 || inputArr[1].trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            int idx = Integer.parseInt(inputArr[1]) - 1;
            taskList.remove(idx);
            storage.writeToFile(taskList);
        }
        case "todo" -> {
            if (inputArr.length == 1 || inputArr[1].trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            Task newTask = new Todo(inputArr[1]);
            taskList.add(newTask);
            storage.writeToFile(taskList);
        }
        case "deadline" -> {
            if (inputArr.length == 1 || inputArr[1].trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            if (!inputArr[1].contains("/by ")) {
                throw new MissingDeadlineByException();
            }
            String[] args = inputArr[1].split(" /by ", 2);
            if (args.length <= 1) {
                throw new DeadlineArgsException();
            }
            String name = args[0];
            String deadline = args[1];
            if (name.trim().isEmpty() || deadline.trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                Task newTask = new Deadline(name, LocalDateTime.parse(deadline, formatter));
                taskList.add(newTask);
                storage.writeToFile(taskList);
            } catch (DateTimeParseException e) {
                System.out.println(
                        "Error: Unable to parse datetime. Enter date time in yyyy-MM-dd HH:mm format");
            }
        }
        case "event" -> {
            if (inputArr.length == 1 || inputArr[1].trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            if (!inputArr[1].contains("/from ")) {
                throw new MissingEventFromException();
            }
            if (!inputArr[1].contains(" /to")) {
                throw new MissingEventToException();
            }
            String[] args = inputArr[1].split(" /from ", 2);
            if (args.length <= 1) {
                throw new EventArgsException();
            }
            String name = args[0];
            String[] fromTo = args[1].split(" /to ", 2);
            if (fromTo.length <= 1) {
                throw new EventArgsException();
            }
            String from = fromTo[0];
            String to = fromTo[1];
            if (name.trim().isEmpty() || from.trim().isEmpty() || to.trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                Task newTask = new Event(name, LocalDateTime.parse(from, formatter),
                        LocalDateTime.parse(to, formatter));
                taskList.add(newTask);
                storage.writeToFile(taskList);
            } catch (DateTimeParseException e) {
                System.out.println(
                        "Error: Unable to parse datetime. Enter date time in yyyy-MM-dd HH:mm format");
            }
        }
        default -> throw new InvalidCommandException();
        }
    }
}
