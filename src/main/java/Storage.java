import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private static final Path workingDir = Paths.get(System.getProperty("user.dir"));
    private static final Path dataDir = workingDir.resolve("data");
    private static final Path storeFile = dataDir.resolve("store.txt");

    public static final String SPECIAL_CHAR = "%%";

    public Storage() {
        validateStorageFile();
    }

    private void validateStorageFile() {
        try {
            if (Files.notExists(dataDir)) {
                Files.createDirectories(dataDir);
            }

            if (Files.notExists(storeFile)) {
                Files.createFile(storeFile);
            }

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void appendToStorage(String stringToAdd) {
        try {
            Files.write(storeFile, List.of(stringToAdd), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public void rewriteEntireFile(ArrayList<Task> taskList) {
        try {
            List<String> lines = taskList.stream()
                    .map(Task::getStorageString)
                    .toList();
            Files.write(storeFile, lines, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("An error occurred while rewriting the file: " + e.getMessage());
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> data = new ArrayList<>();
        try {
            List<String> fileLines = Files.readAllLines(storeFile);
            if (fileLines.isEmpty()) {
                return data;
            }
            for (String line : fileLines) {
                String[] values = line.split(SPECIAL_CHAR);
                String type = values[0];
                switch (type) {
                    case "T" -> data.add(new ToDoTask(values[2], values[1]));
                    case "D" -> data.add(new DeadlineTask(values[2], LocalDate.parse(values[3]), values[1]));
                    case "E" -> data.add(new EventTask(values[2], values[3], values[4], values[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the file: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("The data stored in the storage file is outdated and incompatible. Please delete store.txt and try again");
        }
        return data;
    }
}
