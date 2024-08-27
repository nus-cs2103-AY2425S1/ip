package patrick.tasklist;

import patrick.DateFormatChecker;
import patrick.storage.Storage;
import patrick.parser.Parser;
import patrick.ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDateTime from;
    LocalTime to;

    public Event(String description, String from, String to) {
        super(description);
        String format = DateFormatChecker.getDateFormat(from);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern(format));
        this.to = LocalTime.parse(to, DateTimeFormatter.ofPattern("HHmm"));
    }

    @Override
    public String toString() {
        return "E | " + super.toString() + " | " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + "-" + this.to.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    public static void eventTask(String input) throws Parser.PatrickException {
        String newInput = input.replace("event", "");
        Task task;
        if (newInput.isEmpty()) {
            throw new Parser.PatrickException("Event Task Details cannot be empty!!");
        } else if (!newInput.contains("/from")) {
            throw new Parser.PatrickException("You are missing a '/from' in your details!!");
        } else if (!newInput.contains("/to")) {
            throw new Parser.PatrickException("You are missing a 'to' in your details!!");
        } else {
            String taskDescription = newInput.substring(0, newInput.indexOf("/from") - 1);
            if (taskDescription.isEmpty()) {
                throw new Parser.PatrickException("Event Task Description cannot be empty!!");
            } else {
                String from = newInput.substring(newInput.indexOf("/from"), newInput.indexOf("/to") - 1).replace("/from ", "");
                String to = newInput.substring(newInput.indexOf("/to")).replace("/to ", "");
                if (from.isEmpty()) {
                    throw new Parser.PatrickException("You are missing 'from' information from your details!!");
                } else if (to.isEmpty()) {
                    throw new Parser.PatrickException("You are missing 'to' information from your details!!");
                } else if (DateFormatChecker.getDateFormat(from).equals("Unknown Format")) {
                    throw new Parser.PatrickException("Your from format is incorrect.\nType 'formats' for the formats.");
                } else if (DateFormatChecker.getTimeFormat(to).equals("Unknown Format")) {
                    throw new Parser.PatrickException("Your to format is incorrect.\n Format of 'to' is HHmm");
                } else {
                    task = new Event(taskDescription, from, to);
                    Storage.addList(task);
                    Ui.showUserMsg(task.toString());
                    try {
                        Storage.appendToFile("\n" + task.toString());
                    } catch (IOException e) {
                        System.out.println("There is an error: " + e.getMessage());
                    }
                }
            }
        }
    }
}
