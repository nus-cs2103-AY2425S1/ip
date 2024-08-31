import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
            DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            try {
                for (String entry : entries) {
                    String[] parts = entry.split(",");
                    switch (parts[0]) {
                    case "T":
                        tasks.add(new Todo(parts[2], parts[1].equals("X")));
                        break;
                    case "D":
                        tasks.add(new Deadline(parts[2],
                                LocalDate.parse(parts[3], INPUT_FORMAT),
                                parts[1].equals("X")));
                        break;
                    case "E":
                        tasks.add(new Event(parts[2],
                                LocalDate.parse(parts[3], INPUT_FORMAT),
                                LocalDate.parse(parts[4], INPUT_FORMAT),
                                parts[1].equals("X")));
                        break;
                    }
                }
            } catch (DateTimeParseException e) {
                System.out.println("List of tasks cant be fully loaded because date is in the wrong format");
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
