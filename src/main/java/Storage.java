import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String SAVE_FILE_NAME = "snah.txt";

    public Storage() {
        try {
            File file = new File(SAVE_FILE_NAME);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    public ArrayList<Task> getTaskLists() {
        ArrayList<Task> tasksList = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(SAVE_FILE_NAME));
            for (String line : lines) {
                String[] data = line.split(":");

                if (data[0].startsWith("T")) {
                    ToDo newTask = new ToDo(data[2]);
                    if (data[1].equals("x")) {
                        newTask.markAsDone();
                    }
                    tasksList.add(newTask);
                } else if (data[0].startsWith("D")) {
                    Task newTask = new Deadline(data[2], data[3]);
                    if (data[1].equals("x")) {
                        newTask.markAsDone();
                    }
                    tasksList.add(newTask);
                } else if (data[0].startsWith("E")) {
                    Task newTask = new Event(data[2], data[3], data[4]);
                    if (data[1].equals("x")) {
                        newTask.markAsDone();
                    }
                    tasksList.add(newTask);
                }

            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return tasksList;
    }

    public void saveTaskList(ArrayList<Task> tasksList) {
        try {
            Files.newBufferedWriter(Paths.get(SAVE_FILE_NAME),
                    StandardOpenOption.TRUNCATE_EXISTING).close();

            for (Task task : tasksList) {
                String content = task.toSaveFile() + System.lineSeparator();
                Files.write(Paths.get("snah.txt"), content.getBytes(), StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

}
