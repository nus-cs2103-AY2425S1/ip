import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Deadline extends Task{

    protected LocalDateTime byTime;

    public Deadline(String description, String byTime, boolean isDone) {
        super(description, isDone);
        this.byTime = LocalDateTime.parse(byTime, formatter);
    }

    public Deadline(String description, String byTime) {
        super(description);
        this.byTime = LocalDateTime.parse(byTime, formatter);
        this.saveToFile();
    }

    @Override
    public String toString() {
        return "[D]" + (isDone ? "[X] " : "[ ] ") + this.description + " (by:" + this.byTime.format(formatter) + ")";
    }

    @Override
    public void saveToFile() {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);
            fw.write("D | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.byTime.format(formatter) + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: "+ e.getMessage());
        }
    }

}
