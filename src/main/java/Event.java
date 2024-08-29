import java.io.FileWriter;
import java.io.IOException;

public class Event extends Task {

    protected String fromTime;
    protected String toTime;

    public Event(String description, String fromTime, String toTime, boolean isDone) {
        super(description.trim(), isDone);
        this.fromTime = fromTime.trim();
        this.toTime = toTime.trim();
    }

    public Event(String description, String fromTime, String toTime) {
        super(description.trim());
        this.fromTime = fromTime.trim();
        this.toTime = toTime.trim();
        this.saveToFile();
    }

    @Override
    public String toString() {
        return "[E]" + (isDone ? "[X] " : "[ ] ") + this.description + " (from:" + this.fromTime + " to:" + this.toTime + ")";
    }

    @Override
    public void saveToFile() {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);
            fw.write("E | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.fromTime + " | " + this.toTime + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: "+ e.getMessage());
        }
    }
}
