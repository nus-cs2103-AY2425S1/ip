package task;

import exception.MaxineException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Task {
    protected String description;
    protected boolean isDone;
    protected boolean isDeleted;
    protected int num;
    private static int count;

    private static final List<DateTimeFormatter> FORMATS = new ArrayList<>();

    static {
        FORMATS.add(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        FORMATS.add(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        FORMATS.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        FORMATS.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public Task() {
        this.description = null;
        this.isDone = false;
        count += 1;
        this.num = count;
    }

    public Task(String description) {
        this.description = description.toLowerCase();
        this.isDone = false;
        count += 1;
        this.num = count;
    }

    public void changeStatus() {
        this.isDone = !isDone;

        if (isDone) {
            System.out.println("Yay! You finally did something today");
            System.out.println(this);
        } else {
            System.out.println("Skill issue...");
            System.out.println(this);
        }
    }

    public void delete() {
        this.isDeleted = true;
    }

    public String getStatusIcon() {
        return isDone ? "[X] " : "[ ] "; // mark done task with X
    }
    
    public String writeToFile() {
        return " / " + (isDone ? 1 : 0) + " / " + description;
    }

    @Override
    public String toString() {
        return getStatusIcon() + description;
    }

    public String dateTimeParser(String dateTime) throws MaxineException{
        for (DateTimeFormatter formatter : FORMATS) {
            try {
                // Check if the formatter pattern includes time
                if (formatter.toString().contains("HH:mm")) {
                    // Try parsing as LocalDateTime
                    LocalDateTime deadline = LocalDateTime.parse(dateTime, formatter);
                    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
                    return deadline.format(outputFormatter);
                } else {
                    // Try parsing as LocalDate
                    LocalDate deadline = LocalDate.parse(dateTime, formatter);
                    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
                    return deadline.format(outputFormatter);
                }
            } catch (DateTimeParseException e) {
                // Continue checking with other formats
            }
        }
        // To be changed
        throw new MaxineException("");
    }
    
}