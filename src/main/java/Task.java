import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private String description;
    private boolean isDone;
    private static DateTimeFormatter fileDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static Task fromString(String string) {
        String[] parts = string.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case "T" -> {
                return new ToDo(description);
            }
            case "D" -> {
                LocalDateTime dateAndTime = LocalDateTime.parse(parts[3], fileDateFormat);
                Deadline deadline = new Deadline(description, dateAndTime);
                if (isDone) {
                    deadline.mark();
                }
                return deadline;
            }
            case "E" -> {
                String[] duration = parts[3].split("-");
                LocalDateTime from = LocalDateTime.parse(duration[0], fileDateFormat);
                LocalDateTime to = LocalDateTime.parse(duration[1], fileDateFormat);
                Event event = new Event(description, from, to);
                if (isDone) {
                    event.mark();
                }
                return event;
            }
            default -> {
                throw new IllegalArgumentException("Bao doesn't know what this task type is");
            }
        }
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "1 | " + description;
        } else {
            return "0 | " + description;
        }
    }

    public String toFileString() {
        if (isDone) {
            return "1 | " + description;
        } else {
            return "0 | " + description;
        }
    }
}
