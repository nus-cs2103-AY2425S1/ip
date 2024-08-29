import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime fromTime;
    protected LocalDateTime toTime;

    public Event(String description, String fromTime, String toTime, boolean isDone) {
        super(description.trim(), isDone);
        this.fromTime = LocalDateTime.parse(fromTime, formatter);
        this.toTime = LocalDateTime.parse(toTime, formatter);
    }

    public Event(String description, String fromTime, String toTime) {
        super(description.trim());
        this.fromTime = LocalDateTime.parse(fromTime, formatter);
        this.toTime = LocalDateTime.parse(toTime, formatter);
        this.saveToFile();
    }

    @Override
    public String toString() {
        return "[E]" + (isDone ? "[X] " : "[ ] ") + this.description + " (from:" + this.fromTime.format(formatter) + " to:" + this.toTime.format(formatter) + ")";
    }

    @Override
    public void saveToFile() {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);
            fw.write("E | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.fromTime.format(formatter) + " | " + this.toTime.format(formatter) + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: "+ e.getMessage());
        }
    }
}
