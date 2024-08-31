package chatbot.util;

import chatbot.util.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss");

    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        try {
            this.from = LocalDateTime.parse(from.trim() + "T00:00");
            this.to = LocalDateTime.parse(to.trim() + "T00:00");
        } catch (DateTimeParseException d) {
            try {
                String[] parts = from.split(" ");
                String timeFrom = parts[0] + "T" + parts[1];
                this.from = LocalDateTime.parse(timeFrom);
                String[] part = to.split(" ");
                String timeTo = part[0] + "T" + part[1];
                this.to = LocalDateTime.parse(timeTo);
            } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Mannnn!!! Not in proper Formattt. It should be YYYY-MM_DD<space>HH:MM.\n" +
                        "Or just input the date YYYY-MM-DD. I will take care of rest");
            }
        }
        try {
            this.to = LocalDateTime.parse(to.trim() + "T00:00");
        } catch (DateTimeParseException d) {
            try {
                String[] parts = to.split(" ");
                String time = parts[0] + "T" + parts[1];
                this.to = LocalDateTime.parse(time);
            } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Mannnn!!! Not in proper Formattt. It should be YYYY-MM_DD<space>HH:MM.\n" +
                        "Or just input the date YYYY-MM-DD. I will take care of rest");
            }
        }
    }

    @Override
    public String getTaskInfo() {
        return "EVENT|" + String.valueOf(isDone ? 1 : 0) + "|" + this.description + "|" +
                String.valueOf(this.from).replace("T", " ") + "|"
                + String.valueOf(this.to).replace("T", " ") + "\n";
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (from: " + this.from.format(formatter) + " to: "
                + this.to.format(formatter) + ")";
    }
}
