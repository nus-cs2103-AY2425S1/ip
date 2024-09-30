package BRMO;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import BRMO.task.Deadline;
import BRMO.task.Event;
import BRMO.task.Task;
import BRMO.task.Todo;

/**
 * The Storage class handles loading and saving tasks to and from a file.
 * It manages the reading and writing of tasks in a specific format for persistence.
 */
public class Storage {
    private File file;

    /**
     * Constructs a Storage object that points to the specified file path.
     *
     * @param filePath the file path where tasks are stored
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Loads the tasks from the file and returns them as an ArrayList of Task objects.
     * If the file does not exist, it will create the necessary directories and a new file.
     *
     * @return an ArrayList of Task objects loaded from the file
     * @throws IOException if there is an error accessing or creating the file
     * @throws ParseException if there is an error parsing the task data
     * @throws InvalidCommandException if an unknown task type is encountered
     */
    public ArrayList<Task> load() throws IOException, ParseException, InvalidCommandException {
        ArrayList<Task> tasks = new ArrayList<>();

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] words = line.split(" \\| ");
            assert words.length >= 3 : "Invalid task format in file";

            switch (words[0]) {
            case "T":
                tasks.add(new Todo(words[2]));
                break;
            case "D":
                tasks.add(new Deadline(words[2], words[3]));
                break;
            case "E":
                tasks.add(new Event(words[2], words[3], words[4]));
                break;
            default:
                throw new InvalidCommandException("Unknown task type: " + words[0]);
            }

            if (words[1].equals("1")) {
                tasks.get(tasks.size() - 1).mark();
            }
        }
        fileScanner.close();

        return tasks;
    }
    
    /**
     * Saves the tasks to the file. Each task is saved in a format that can be loaded later.
     *
     * @param tasks the ArrayList of Task objects to be saved
     * @throws IOException if there is an error writing to the file
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        PrintWriter writer = new PrintWriter(file);
        for (Task task : tasks) {
            writer.println(task.toText());
        }
        writer.close();
    }
}
