package chatbot.util;

import chatbot.util.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime by;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss");

    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        try {
            this.by = LocalDateTime.parse(by.trim() + "T00:00");
        } catch (DateTimeParseException d) {
            try {
                String[] parts = by.split(" ");
                String time = parts[0] + "T" + parts[1];
                this.by = LocalDateTime.parse(time);
            } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Mannnn!!! Not in proper Formattt. It should be YYYY-MM_DD<space>HH:MM.\n" +
                        "Or just input the date YYYY-MM-DD. I will take care of rest");
            }
        }
    }

    @Override
    public String getTaskInfo() {
        return "DEADLINE|" + String.valueOf(isDone ? 1 : 0) + "|" + this.description + "|"
                + String.valueOf(this.by).replace("T", " ") + "\n";
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by.format(formatter) + ")";
    }

}
