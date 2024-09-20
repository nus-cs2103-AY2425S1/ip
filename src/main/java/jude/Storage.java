package jude;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jude.task.Deadline;
import jude.task.Event;
import jude.task.Task;
import jude.task.Todo;

/**
 * Handles the save file of the Tasks.
 * <p>
 * This class loads the previous data by reading from the save file to remember the TaskList created
 * in the previous run.
 * It creates a new save file if the save file does not exist.
 * It saves the file by writing the task datas in the save file, so that it can be loaded the next time.
 */
public class Storage {
    private String filePath;
    private Scanner fileReader;
    private FileWriter writer;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the save file. Creates the file if the file does not exist in the filePath.
     *
     * @return TaskList containing the information read from the save file.
     * @throws JudeException If there was error loading the file.
     */
    public List<Task> load() throws JudeException {
        if (!fileExists()) {
            return new ArrayList<>();
        }

        try {
            initializeFileReader();
            return readTasks();
        } catch (FileNotFoundException fe) {
            throw new JudeException("file not found error while loading the file. Starts with an empty list.");
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
        }
    }

    private List<Task> readTasks() throws JudeException {
        List<Task> list = new ArrayList<>();
        addTasks(list);
        return list;
    }

    private void initializeFileReader() throws FileNotFoundException {
        File file = new File(filePath);
        fileReader = new Scanner(file);
    }

    private boolean fileExists() {
        File file = new File(filePath);
        return file.exists();
    }

    private void addTasks(List<Task> list) throws JudeException {
        while (fileReader.hasNextLine()) {
            String[] attributes = getCurrentLineTaskFormat();
            Task task = createTaskFromAttributes(attributes, list);
            list.add(task);
        }
    }

    private String[] getCurrentLineTaskFormat() {
        return fileReader.nextLine().split(" \\| ");
    }

    private Task createTaskFromAttributes(String[] splits, List<Task> list) throws JudeException {
        String type = splits[0];
        boolean isDone = splits[1].equals("1");
        String description = splits[2];

        switch (type) {
        case "T":
            return new Todo(description, isDone);
        case "D":
            return new Deadline(description, splits[3], isDone);
        case "E":
            return new Event(description, splits[3], splits[4], isDone);
        default:
            throw new JudeException("Unknown task type found in save file.");
        }
    }

    /**
     * Saves the current Tasklist to the save file.
     *
     * @param list The TaskList created during the execution of Jude chatbot program.
     * @throws JudeException If there was an error while creating a save file.
     */
    public void save(TaskList list) throws JudeException {
        try {
            writeToFile(list);
        } catch (IOException ie) {
            throw new JudeException("IOException has occurred while writing to a save file.");
        }
    }

    private void writeToFile(TaskList list) throws IOException {
        writer = new FileWriter(filePath);
        writer.write(list.toFileFormat());
        writer.close();
    }
}
