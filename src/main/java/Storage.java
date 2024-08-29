import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Appends desired String to the file.
     *
     * @param textToAppend The String to be appended into the file.
     * @throws IOException Input / Output exceptions.
     */
    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    public void loadTasks(TaskList tasks) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner scanner = new Scanner(f);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] taskData = line.split("\\|");

            String type = taskData[0];
            boolean isDone = taskData[1].equals("1");
            String description = taskData[2];

            switch (type) {
            case "T":
                Todo t = new Todo(description);
                if (isDone) {
                    t.markAsDone();
                }
                tasks.addTask(t);
                break;

            case "D":
                String deadline = taskData[3];
                Deadline d = new Deadline(description, deadline);
                if (isDone) {
                    d.markAsDone();
                }
                tasks.addTask(d);
                break;

            case "E":
                String from = taskData[3];
                String to = taskData[4];
                Event e = new Event(description, from, to);
                if (isDone) {
                    e.markAsDone();
                }
                tasks.addTask(e);
                break;

            default:
                System.out.println("Invalid task found!");
                break;
            }
        }
    }
}
