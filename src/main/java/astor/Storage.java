package astor;

import astor.task.Deadline;
import astor.task.Event;
import astor.task.Task;
import astor.task.Todo;

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

/**
 * Represents a storage manager.
 *
 * The storage manager connects to datafile and creates it if absent,
 * writes to datafile, reads from datafile.
 */
public class Storage {

    private FileWriter fw;
    private File file;

    /**
     * Assigns the created data store from the filepath to a filewrite for processing later.
     *
     * @param filePath
     */
    public Storage(String filePath) {
        try {
            this.fw = createNewDataStore(filePath);
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
        fw.write(textToAppend + "\n");
    }

    /**
     * Clears the file and write everything from list of tasks to the new data file
     *
     * @param tasks the list of tasks to append to the empty file
     */
    public void updateData(List<Task> tasks) {
        try {
            fw = new FileWriter(file);
            fw = new FileWriter(file, true);
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
        this.fw.close();
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
                String[] info = line.split(" \\| ");
                if (info[0].equals("T")) {
                    Task task = new Todo(info[2]);
                    if (info[1].equals("1")) {
                        task.markDone();
                    }
                    tasks.add(task);
                } else if (info[0].equals("D")) {
                    Task task = new Deadline(info[2], LocalDateTime.parse(info[3]));
                    if (info[1].equals("1")) {
                        task.markDone();
                    }
                    tasks.add(task);
                } else {
                    Task task = new Event(info[2], LocalDateTime.parse(info[3]), LocalDateTime.parse(info[4]));
                    if (info[1].equals("1")) {
                        task.markDone();
                    }
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred while reading the file: " + e.getMessage());
        }
        return tasks;
    }
}
