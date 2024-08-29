import java.io.FileWriter;
import java.io.IOException;

public class Deadline extends Task{

    protected String byTime;

    public Deadline(String description, String byTime, boolean isDone) {
        super(description, isDone);
        this.byTime = byTime;
    }

    public Deadline(String description, String byTime) {
        super(description);
        this.byTime = byTime;
        this.saveToFile();
    }

    @Override
    public String toString() {
        return "[D]" + (isDone ? "[X] " : "[ ] ") + this.description + " (by:" + this.byTime + ")";
    }

    @Override
    public void saveToFile() {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);
            fw.write("D | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.byTime + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: "+ e.getMessage());
        }
    }

}
