package xbot.parser;

import xbot.storage.Storage;
import xbot.ui.Ui;
import xbot.XBotException;
import xbot.TaskList;

import xbot.task.Task;
import xbot.task.ToDo;
import xbot.task.Event;
import xbot.task.Deadline;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length >= 3) {
            String type = parts[0].trim();
            boolean isDone = parts[1].trim().equals("1");
            String description = parts[2].trim();
            switch (type) {
            case "T":
                Task todo = new ToDo(description);
                if (isDone) todo.setIsDone();
                return todo;
            case "D":
                String deadline = parts[3].trim();
                Task deadlineTask = new Deadline(description, deadline);
                if (isDone) deadlineTask.setIsDone();
                return deadlineTask;
            case "E":
                String from = parts[3].trim();
                String to = parts[4].trim();
                Task eventTask = new Event(description, from, to);
                if (isDone) eventTask.setIsDone();
                return eventTask;
            default:
                System.out.println("Unknown task type: " + type);
            }
        }
        return null;
    }

    public static boolean isValidDateFormat(String date) {
        List<String> formats = new ArrayList<>();
        formats.add("yyyy-MM-dd");
        formats.add("d/M/yyyy");
        formats.add("d/M/yyyy HHmm");

        for (String format : formats) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            try {
                if (format.contains("HHmm")) {
                    LocalDateTime.parse(date, formatter);
                } else {
                    LocalDate.parse(date, formatter);
                }
                return true;
            } catch (DateTimeParseException e) {
                //Do nothing
            }
        }
        return false;
    }

    public static String taskToFileString(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(task.getType() + " | ");
        sb.append(task.isDone() ? "1 | " : "0 | ");
        sb.append(task.getDescription());
        if (task instanceof Deadline) {
            sb.append(" | ").append(((Deadline) task).getBy());
        } else if (task instanceof Event) {
            sb.append(" | ").append(((Event) task).getFrom())
                    .append(" | ").append(((Event) task).getTo());
        }
        return sb.toString();
    }
    public void processInput(String input, TaskList list, Ui ui, Storage storage) throws XBotException {
        String[] words = input.split("\\s+", 2);
        String command = words[0].toLowerCase();
        String rest = words.length > 1 ? words[1] : "";
        switch(command) {
        case "list":
            ui.showTaskList(list);
            break;
        case "mark":
            list.markDone(rest);
            storage.saveTask(list);
            break;
        case "unmark":
            list.markUndone(rest);
            storage.saveTask(list);
            break;
        case "todo":
            if (rest.isEmpty()) {
                throw new XBotException("The description of the todo cannot be empty!");
            }
            list.addTodo(rest);
            storage.saveTask(list);
            break;
        case "event":
            if (rest.isEmpty()) {
                throw new XBotException("The description of the event cannot be empty!");
            }
            list.addEvent(rest);
            storage.saveTask(list);
            break;
        case "deadline":
            if (rest.isEmpty()) {
                throw new XBotException("The description of the deadline cannot be empty!");
            }
            list.addDeadline(rest);
            storage.saveTask(list);
            break;
        case "delete":
            if (rest.isEmpty()) {
                throw new XBotException("The task number to be deleted cannot be empty!");
            }
            list.deleteTask(rest);
            storage.saveTask(list);
            break;
        default:
            throw new XBotException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
