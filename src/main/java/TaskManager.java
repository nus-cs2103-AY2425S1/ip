import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private static final String filePath = "./data/bottle.txt";

    private void handleMissingFile() {
        Path path = Paths.get(filePath);
        try {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        } catch (IOException e){
            System.out.println("Error occurred creating file");
        }
    }
    public ArrayList<Task> loadTasks() {
        File file = new File(filePath);
        ArrayList<Task> taskList = new ArrayList<>();
        if (!file.exists()) {
            handleMissingFile();
        } else {
            try {
                List<String> lines = Files.readAllLines(Paths.get(filePath));

                for (String line : lines) {
                   String[] parts = line.split("\\|");
                   Task task;
                   try {
                       task = switch (parts[0]) {
                           case "T " -> new Todo(parts[2]);
                           case "D " -> new Deadline(parts[2], parseDateTime(parts[3]));
                           case "E " -> new Event(parts[2], parseDateTime(parts[3]), parseDateTime(parts[4]));
                           default -> throw new IllegalArgumentException("Wrong input format");
                       };
                       if (parts[1].equals(" 1 ")) {
                           task.mark();
                       } else if (parts[1].equals(" 0 ")) {
                           task.unMark();
                       } else {
                           throw new IllegalArgumentException("Wrong isMarked input format");
                       }
                       taskList.add(task);
                   } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
                       System.out.println("Wrong input format" + e.getMessage());
                   }
                }
            } catch (IOException e) {
                System.out.println("Error reading file");
            }
        }
        return taskList;
    }
    public void saveTasks(ArrayList<Task> taskList) {
        File file = new File(filePath);
        if (!file.exists()) {
            handleMissingFile();
        }
        try {
            FileWriter fileWriter =  new FileWriter(file);
            for (Task task : taskList) {
                fileWriter.write(task.toSaveFormat() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving file");
        }
    }
    private static LocalDateTime parseDateTime(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("OOPS!!! The date format is incorrect. Please use: dd/MM/yyyy HHmm");
        }
    }
}
