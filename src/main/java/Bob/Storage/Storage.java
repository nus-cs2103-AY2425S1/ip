package bob.Storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import bob.Tasks.Deadline;
import bob.Tasks.Event;
import bob.Tasks.Task;
import bob.Tasks.Todo;
import bob.Exception.BobException;


public class Storage {
    private String filePath;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws BobException {
        ArrayList<Task> tasks = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adjusted formatter

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");

                Task task;
                switch (taskType) {
                    case "T":
                        task = new Todo(parts[2]);
                        break;
                    case "D":
                        String dateString = parts[3];
                        LocalDateTime deadlineDateTime;
                        try {
                            // Try parsing as LocalDateTime first
                            deadlineDateTime = LocalDateTime.parse(dateString, dateTimeFormatter);
                        } catch (DateTimeParseException e) {
                            try {
                                // If it fails, try parsing as LocalDate and convert to LocalDateTime
                                LocalDate deadlineDate = LocalDate.parse(dateString, dateFormatter);
                                deadlineDateTime = deadlineDate.atStartOfDay();
                            } catch (DateTimeParseException ex) {
                                throw new BobException("Invalid date format in file!");
                            }
                        }
                        task = new Deadline(parts[2], deadlineDateTime);
                        break;
                    case "E":
                        task = new Event(parts[2], parts[3], parts[4]);
                        break;
                    default:
                        throw new BobException("Unknown task type");
                }

                if (isDone) {
                    task.mark();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new BobException("Failed to load Tasks from file");
        }
        return tasks;
    }


    public void save(ArrayList<Task> tasks) throws BobException {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.fileFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new BobException("Failed to save Tasks to file");
        }
    }
}
