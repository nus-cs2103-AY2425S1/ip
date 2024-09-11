import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws NaegaException {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] taskDetails = line.split(" \\| ");
                Task task = parseTask(taskDetails);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            // No file exists yet, so start with an empty list
        } catch (IOException e) {
            throw new NaegaException("Error reading from file: " + e.getMessage());
        }
        return tasks;
    }

    private Task parseTask(String[] taskDetails) throws NaegaException {
        System.out.println("taskDetails length: " + taskDetails.length);
        System.out.println("taskDetails: " + Arrays.toString(taskDetails));

        String taskType = taskDetails[0];
        String description = taskDetails[2];

        // Define the date-time format (e.g., "6/9/2023 1400")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        switch (taskType) {
            case "T":
                return new Todo(description);
            case "D":
                if (taskDetails.length < 4) {
                    throw new NaegaException("Insufficient details for Deadline task.");
                }
                // Parse deadline with custom formatter
                LocalDateTime deadline = LocalDateTime.parse(taskDetails[3], formatter);
                return new Deadline(description, deadline);
            case "E":
                if (taskDetails.length < 5) {
                    throw new NaegaException("Insufficient details for Event task.");
                }
                // Parse event start and end times with custom formatter
                LocalDateTime eventStart = LocalDateTime.parse(taskDetails[3], formatter);
                LocalDateTime eventEnd = LocalDateTime.parse(taskDetails[4], formatter);
                return new Event(description, eventStart, eventEnd);
            default:
                throw new NaegaException("Invalid task type in file.");
        }
    }

    public void save(ArrayList<Task> tasks) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.println(task.toSaveFormat());
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}