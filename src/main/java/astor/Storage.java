package astor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import astor.task.Deadline;
import astor.task.Event;
import astor.task.Task;
import astor.task.Todo;

/**
 * Represents a storage manager.
 *
 * The storage manager connects to datafile and creates it if absent,
 * writes to datafile, reads from datafile.
 */
public class Storage {

    private FileWriter filewriter;
    private File file;

    /**
     * Assigns the created data store from the filepath to a filewrite for processing later.
     *
     * @param filePath
     */
    public Storage(String filePath) {
        assert filePath != null : "filePath cannot be null";
        try {
            this.filewriter = createNewDataStore(filePath);
        } catch (IOException e) {
            System.out.println("Error occurred while loading the file: " + e.getMessage());
        }
    }

    public Storage() {

    }


    /**
     * Checks if the file exists, if not, create the file and assigns it to field variable
     * @param filePath the string that determine where the data file is stored
     * @return a filewriter that allows for writing to file
     * @throws IOException when there is interrupted io operations
     */
    private FileWriter createNewDataStore(String filePath) throws IOException {
        assert filePath != null : "filePath cannot be null";
        String directoryPath = "./data";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        Path path = Paths.get(filePath);
        File file = path.toFile();
        if (!file.exists()) {
            file.createNewFile();
        }
        this.file = file;
        return new FileWriter(file, true);
    }

    /**
     * Adds a string to the end of the file
     *
     * @param textToAppend the text to append to the file
     * @throws IOException if an io error occurs when writing to the file
     */
    public void appendToFile(String textToAppend) throws IOException {
        filewriter.write(textToAppend + "\n");
    }

    /**
     * Clears the file and write everything from list of tasks to the new data file
     *
     * @param tasks the list of tasks to append to the empty file
     */
    public void updateData(List<Task> tasks) {
        try {
            filewriter = new FileWriter(file);
            filewriter = new FileWriter(file, true);
            for (Task task : tasks) {
                appendToFile(task.dataDescription());
            }
        } catch (IOException e) {
            System.out.println("Error occurred while writing to file: " + e.getMessage());
        }
    }

    /**
     * Ends the filewriter
     *
     * @throws IOException if the io operation is interrupted
     */
    public void completeModification() throws IOException {
        this.filewriter.close();
    }

    /**
     * Extracts all the stored data in the data file to a list of tasks
     *
     * @return the list of tasks represented by the text data file
     */
    public List<Task> getData() {
        List<Task> tasks = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task newTask = createTask(line);
                assert newTask != null : "newTask cannot be null";
                tasks.add(newTask);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred while reading the file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Creates a {@code Task} object based on the provided input string.
     * <p>
     * This method interprets a formatted string that represents a task, which can be
     * a {@code Todo}, {@code Deadline}, or {@code Event}. The string is split using
     * the delimiter " | " to extract the task type, completion status, and task details.
     * <ul>
     *   <li>If the task type is "T", a {@code Todo} task is created.</li>
     *   <li>If the task type is "D", a {@code Deadline} task with a specific due date is created.</li>
     *   <li>If the task type is "E", an {@code Event} task with a start and end time is created.</li>
     * </ul>
     * If the completion status is "1", the task will be marked as done.
     *
     * @param line the formatted string representing a task, split by " | "
     *             where the first part indicates the task type (T/D/E),
     *             the second part indicates whether the task is completed ("1" for done, "0" for not done),
     *             and the subsequent parts contain the task details
     * @return the newly created {@code Task} object, either a {@code Todo}, {@code Deadline}, or {@code Event}
     */
    public Task createTask(String line) {
        assert line != null : "line cannot be null";
        assert line.charAt(0) == 'T' || line.charAt(0) == 'D' || line.charAt(0) == 'E' : "invalid stored format";

        String[] info = line.split(" \\| ");
        if (info[0].equals("T")) {
            Task task = new Todo(info[2]);
            if (info[1].equals("1")) {
                task.markDone();
            }
            return task;
        } else if (info[0].equals("D")) {
            Task task = new Deadline(info[2], LocalDateTime.parse(info[3]));
            if (info[1].equals("1")) {
                task.markDone();
            }
            return task;
        } else if (info[0].equals("E")) {
            Task task = new Event(info[2], LocalDateTime.parse(info[3]), LocalDateTime.parse(info[4]));
            if (info[1].equals("1")) {
                task.markDone();
            }
            return task;
        } else {
            return null;
        }
    }
}
