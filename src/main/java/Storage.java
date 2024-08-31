import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws LunaBotException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        File directory = new File(file.getParent());

        if (!directory.exists()) {
            directory.mkdir();
        }

        if (!file.exists()) {
            return taskList;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(" \\| ");
                String taskType = arr[0];
                boolean isDone = arr[1].equals("1");
                String description = arr[2];
                LocalDateTime dateTime = null;

                if (arr.length > 3) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                    dateTime = LocalDateTime.parse(arr[3], formatter);
                }

                switch (taskType) {
                    case "T":
                        taskList.add(new ToDo(description, isDone));
                        break;
                    case "D":
                        taskList.add(new Deadline(description, dateTime, isDone));
                        break;
                    case "E":
                        LocalDateTime from = LocalDateTime.parse(arr[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                        LocalDateTime to = LocalDateTime.parse(arr[4], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                        taskList.add(new Event(description, from, to, isDone));
                        break;
                    default:
                        throw new LunaBotException("Invalid task type found in file");
                }
            }
        }
        catch (IOException e) {
            throw new LunaBotException("Error reading file: " + e.getMessage());
        }
        return taskList;
    }

    public void save(ArrayList<Task> taskList) throws LunaBotException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : taskList) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        }
        catch (IOException e) {
            throw new LunaBotException("Error writing to file: " + e.getMessage());
        }
    }
}
