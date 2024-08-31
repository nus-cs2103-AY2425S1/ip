import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (file.exists()) {
            List<String> entries = Files.readAllLines(Paths.get(filePath));

            for (String entry : entries) {
                String[] parts = entry.split(",");
                switch (parts[0]) {
                case "T":
                    tasks.add(new Todo(parts[2], parts[1].equals("X")));
                    break;
                case "D":
                    tasks.add(new Deadline(parts[2], parts[3], parts[1].equals("X")));
                    break;
                case "E":
                    tasks.add(new Event(parts[2], parts[3],
                            parts[4], parts[1].equals("X")));
                    break;
                }
            }
        } else {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);

        for (Task task : tasks) {
            writer.write(task.commaString() + System.lineSeparator());
        }

        writer.close();
    }

}
