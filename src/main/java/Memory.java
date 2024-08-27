import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Memory {
    private final String filePath;

    public Memory(String pathName) {
        this.filePath = pathName;
    }

    public List<Task> load() throws IOException, SageException {
        List<Task> taskList = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            throw new SageException("File does not exist.");
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String entry = scanner.nextLine();
            String[] entryDetails = entry.split(" \\| ");

            String type = entryDetails[0];
            boolean isDone = entryDetails[1].equals("1");
            String description = entryDetails[2];

            switch (type) {
                case "T":
                    Task toDO = new ToDo(description);
                    toDO.setDone(isDone);
                    taskList.add(toDO);
                    break;
                case "D":
                    String by = entryDetails[3];
                    Task deadline = new Deadline(description, by);
                    deadline.setDone(isDone);
                    taskList.add(deadline);
                    break;
                case "E":
                    String from = entryDetails[3];
                    String to = entryDetails[4];
                    Task event = new Event(description, from, to);
                    event.setDone(isDone);
                    taskList.add(event);
                    break;
                default:
                    throw new SageException("Corrupted file.");
            }
        }
        scanner.close();
        return taskList;
    }

    public void save(List<Task> tasksList) throws IOException {
        FileWriter fw  = new FileWriter(filePath);
        for (Task task : tasksList) {
            fw.write(task.toSave() + System.lineSeparator());
        }
        fw.close();
    }
}
