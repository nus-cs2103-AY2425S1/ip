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
 *
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
     * @return TaskList containing the information read from the save file.
     * @throws JudeException if there was error loading the file.
     */
    public List<Task> load() throws JudeException {
        File file = new File(filePath);
        List<Task> list = new ArrayList<>();
        String errorMessage = " error while loading the file. Starts with an empty list.";

        // Check if file exists
        if (!file.exists()) {
            return list;
        }

        try {
            fileReader = new Scanner(file);
        } catch (FileNotFoundException fe) {
            throw new JudeException("file not found" + errorMessage);
        }

        while (fileReader.hasNextLine()) {
            String[] split = fileReader.nextLine().split(" \\| ");

            try {
                boolean isDone = split[1].equals("1");
                String description = split[2];

                switch (split[0]) {
                case "T":
                    list.add(new Todo(description, isDone));
                    break;
                case "D":
                    list.add(new Deadline(description, split[3], isDone));
                    break;
                case "E":
                    list.add(new Event(description, split[3], split[4], isDone));
                    break;
                default:
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException ae) {
                throw new JudeException("Invalid format was provided in the save file.");
            }
        }
        return list;
    }

    /**
     * Saves the current Tasklist to the save file.
     * @param list the TaskList created during the execution of Jude chatbot program.
     * @throws JudeException if there was an error while creating a save file.
     */
    public void save(TaskList list) throws JudeException {
        // Write to the save file
        try {
            writer = new FileWriter(filePath);
            writer.write(list.toFileFormat());
            writer.close();
        } catch (IOException ie) {
            throw new JudeException("IOException has occurred while writing to a save file.");
        }
    }


}
