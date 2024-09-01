import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    // private final Path path = Paths.get("./src/main/data/Astor.txt");
    private FileWriter fw;
    private File file;

    public Storage(String filePath) {
        try {
            this.fw = createNewDataStore(filePath);
        } catch (IOException e) {
            System.out.println("Error occurred while loading the file: " + e.getMessage());
        }
    }

    private FileWriter createNewDataStore(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        File file = path.toFile();
        if (!file.exists()) {
            file.createNewFile();
        }
        this.file = file;
        return new FileWriter(file, true);
    }

    public void appendToFile(String textToAppend) throws IOException {
        fw.write(textToAppend + "\n");
    }

    public void updateData(List<Task> tasks) {
        try {
            fw = new FileWriter(file);
            fw = new FileWriter(file, true);
            for (Task task : tasks) {
                appendToFile(task.dataDescription());
            }
        } catch (IOException e) {
            System.out.println("Error occurred while writing to file: " + e.getMessage());
        }
    }

    public void completeModification() throws IOException {
        this.fw.close();
    }

    public List<Task> getData() {
        List<Task> tasks = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] info = line.split(" \\| ");
                if (info[0].equals("T")) {
                    Task task = new Todo(info[2]);
                    if (info[1].equals("1")) {
                        task.markDone();
                    }
                    tasks.add(task);
                } else if (info[0].equals("D")) {
                    Task task = new Deadline(info[2], LocalDateTime.parse(info[3]));
                    if (info[1].equals("1")) {
                        task.markDone();
                    }
                    tasks.add(task);
                } else {
                    Task task = new Event(info[2], LocalDateTime.parse(info[3]), LocalDateTime.parse(info[4]));
                    if (info[1].equals("1")) {
                        task.markDone();
                    }
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred while reading the file: " + e.getMessage());
        }
        return tasks;
    }
}
