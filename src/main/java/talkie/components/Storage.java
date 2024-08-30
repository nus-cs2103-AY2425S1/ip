package talkie.components;

import talkie.exception.TalkieNoTaskFoundException;
import talkie.task.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and saving of tasks to and from a file.
 * <p>
 * The {@code Storage} class is responsible for reading tasks from a file and
 * writing tasks to a file. It supports creating a new file if one does not exist.
 * </p>
 */
public class Storage {

    private String filePath;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     *
     * @param filePath The path of the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by {@code filePath}.
     * <p>
     * Reads each line from the file, parses it into a {@link Task} object, and adds it to an {@code ArrayList}.
     * If the file does not exist, a new file is created.
     * </p>
     *
     * @return An {@code ArrayList} of tasks loaded from the file.
     * @throws TalkieNoTaskFoundException If an error occurs while parsing a task entry.
     */
    public ArrayList<Task> loadData() throws TalkieNoTaskFoundException {
        File database = new File(this.filePath);
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            Scanner fileReader = new Scanner(database);
            while (fileReader.hasNextLine()) {
                String entry = fileReader.nextLine();
                taskList.add(readEntry(entry));
            }
        } catch (FileNotFoundException e) {
            this.createDatabase();
        }

        return taskList;
    }

    /**
     * Parses a task entry from a string and creates a {@link Task} object.
     *
     * @param entry The string containing task data in a specific format.
     * @return The corresponding {@code Task} object.
     * @throws TalkieNoTaskFoundException If the task type is unknown or the format is incorrect.
     */
    private Task readEntry(String entry) throws TalkieNoTaskFoundException {
        String[] fields = entry.split(" \\| ");
        Task taskToBeAdded;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

        switch(fields[0]) {
        case "T":
            taskToBeAdded = new ToDo(fields[2]);
            break;
        case "E":
            taskToBeAdded = new Event(fields[2],
                    LocalDateTime.parse(fields[3], formatter),
                    LocalDateTime.parse(fields[4], formatter));
            break;
        case "D":
            taskToBeAdded = new Deadline(fields[2],
                    LocalDateTime.parse(fields[3], formatter));
            break;
        default:
            throw new TalkieNoTaskFoundException();
        }

        if (Integer.parseInt(fields[1]) == 1) {
            taskToBeAdded.markAsDone();
        }

        return taskToBeAdded;
    }

    /**
     * Creates a new database file if it does not already exist.
     * <p>
     * Creates a directory for the file if it is missing, and then creates a new file.
     * </p>
     */
    private void createDatabase() {
        File db = new File(this.filePath);
        File dir = new File(db.getParent());
        dir.mkdir();

        try {
            db.createNewFile();
        } catch (IOException e) {
            System.out.println("Oops! Something went wrong when creating the database!");
        }
    }

    /**
     * Saves the list of tasks to the file specified by {@code filePath}.
     * <p>
     * Writes each task to the file, overwriting any existing content.
     * </p>
     *
     * @param taskList The list of tasks to be saved.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void saveData(TaskList taskList) throws IOException {
        FileWriter writer = new FileWriter(this.filePath, false);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        for (int i = 1; i <= taskList.size(); i++) {
            bufferedWriter.write(taskList.getTask(i).stringifyTask());
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
        writer.close();
    }
}
