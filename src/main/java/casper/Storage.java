package casper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import exception.CasperBotIoException;
import exception.CasperBotOutOfBoundsException;

/**
 * Represents the Storage class
 */
public class Storage {
    private final String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Opens the file based on filepath
     * After opening file, it will parse the file contents to add them to the task list
     * If file does not exist, it will create a new file
     * @param taskList Task list where file contents will be added to
     * @throws CasperBotIoException If there is an IOException while reading or opening the file
     */
    public void openFile(TaskList taskList) throws CasperBotIoException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) { // Read each line until end of file
                String[] values = line.split("\\|");
                boolean isDone = Boolean.parseBoolean(values[1]);
                String description = values[2];
                switch (values[0]) {
                case "T":
                    taskList.addTask(new ToDo(description, isDone));
                    break;
                case "D":
                    LocalDate deadline = LocalDate.parse(values[3]);
                    taskList.addTask(new Deadline(description, isDone, deadline));
                    break;
                case "E":
                    LocalDate start = LocalDate.parse(values[3]);
                    LocalDate end = LocalDate.parse(values[4]);
                    taskList.addTask(new Event(description, isDone, start, end));
                    break;
                default:
                    throw new CasperBotIoException();
                }
            }
        } catch (FileNotFoundException e) {
            createNewFile(filePath);
        } catch (IOException e) {
            throw new CasperBotIoException();
        }
    }
    private void createNewFile(String filePath) throws CasperBotIoException {
        try {
            File file = new File(filePath);
            file.createNewFile();
        } catch (IOException ioException) {
            throw new CasperBotIoException();
        }
    }

    /**
     * Write a task description to a file for persistent storage
     * @throws CasperBotIoException f there is an IOException while writing to the file
     */
    public void writeToFile(Task task) throws CasperBotIoException {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            boolean isDone = task.getStatusIcon().equals("X");
            String description = task.getDescription();
            String taskString = isDone + "|" + description;
            switch (task.getTaskType()) {
            case "T":
                bufferedWriter.write("T|" + taskString);
                break;
            case "D":
                Deadline deadline = (Deadline) task;
                bufferedWriter.write("D|" + taskString + "|" + deadline.getDeadline());
                break;
            case "E":
                Event event = (Event) task;
                bufferedWriter.write("D|" + taskString + "|" + event.getStart() + "|" + event.getEnd());
                break;
            default:
                throw new CasperBotIoException();
            }
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new CasperBotIoException();
        }
    }

    /**
     * Deletes a line in the file, specified by line number
     * @param line The line number to delete
     * @throws CasperBotIoException If there is an IOException when handling the file (read/write)
     * @throws CasperBotOutOfBoundsException If the line number specified does not exist
     */
    public void deleteFromFile(int line) throws CasperBotIoException, CasperBotOutOfBoundsException {
        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);


            lines.remove(line);
            Files.write(path, lines);
        } catch (IOException e) {
            throw new CasperBotIoException();
        }
    }

    /**
     * Updates the file contents to mark or unmark the done status of a task
     * @param line The line to be updated
     * @param isDone Boolean value of whether the task is done or not
     * @throws CasperBotIoException If there is an IOException when handling the file (read/write)
     * @throws CasperBotOutOfBoundsException If the line number specified does not exist
     */
    public void updateDoneStatus(int line, boolean isDone) throws CasperBotIoException, CasperBotOutOfBoundsException {
        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);
            if (line < 0 || line >= lines.size()) {
                throw new CasperBotOutOfBoundsException();
            }
            String previous = lines.remove(line);
            String[] previousParts = previous.split("\\|");
            previousParts[1] = String.valueOf(isDone);
            String updated = String.join("|", previousParts);
            lines.add(line, updated);
            Files.write(path, lines);
        } catch (IOException e) {
            throw new CasperBotIoException();
        }
    }
}
