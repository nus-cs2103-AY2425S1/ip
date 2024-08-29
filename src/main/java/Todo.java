import java.io.FileWriter;
import java.io.IOException;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
        saveToFile();
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + (isDone ? "[X] " : "[ ] ") + this.description;
    }

    @Override
    public void saveToFile() {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);
            fw.write("T | " + (isDone ? "1" : "0") + " | " + this.description + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}
