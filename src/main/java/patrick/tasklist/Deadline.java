package patrick.tasklist;

import patrick.DateFormatChecker;
import patrick.parser.Parser;
import patrick.storage.Storage;
import patrick.ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    String format;

    public Deadline(String description, String by) {
        super(description);
        format = DateFormatChecker.getDateFormat(by);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern(format));
    }

    @Override
    public String toString() {
        return "D | " + super.toString() + " | " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }

    public static void deadlineTask(String input) throws Parser.PatrickException {
        String newInput = input.replace("deadline", "");
        if (newInput.isEmpty()) {
            throw new Parser.PatrickException("Deadline Task Details cannot be empty!!");
        }
        else if (!newInput.contains("/by")) {
            throw new Parser.PatrickException("You are missing a '/by' in ur details!!");
        }
        else {
            String taskDescription = newInput.substring(0, newInput.indexOf("/") - 1);
            if (taskDescription.isEmpty()) {
                throw new Parser.PatrickException("Deadline Task Description cannot be empty!!");
            }
            else {
                String deadline = newInput.substring(newInput.indexOf("/by")).replace("/by ", "");
                if (deadline.isEmpty()) {
                    throw new Parser.PatrickException("Deadline Task deadline cannot be empty!!");
                } else if (DateFormatChecker.getDateFormat(deadline).equals("Unknown Format")) {
                    throw new Parser.PatrickException("Your deadline format is incorrect.\nType 'formats' for the formats.");
                } else {
                    Task task = new Deadline(taskDescription, deadline);
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
