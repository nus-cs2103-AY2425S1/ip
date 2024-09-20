package swbot.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import swbot.BuzzException;
import swbot.tasks.Deadline;
import swbot.tasks.Event;
import swbot.tasks.Task;
import swbot.tasks.Todo;

/**
 * A class that takes care of loading and saving the tasks to the database of the chatbot
 * so that it can help remember what the user has added to the list in the form of an
 * output file.
 */
public class Storage {
    private String filePath;

    /**
     * Creates a storage object that stores the file to be written and read upon by the chatbot
     *
     * @param file file path that tells the program where to store the output task list
     */
    public Storage(String file) {
        this.filePath = generateFilePath(file);
    }

    /**
     * Generates a file path by appending the given file name to a predefined 'data' directory within the user's
     * current working directory.
     *
     * @param fileName the name of the file for which the path is to be generated
     * @return the full path to the file within the 'data' directory
     */
    private String generateFilePath(String fileName) {
        String userDir = System.getProperty("user.dir");
        String dataDirectory = userDir + File.separator + "data";
        return dataDirectory + File.separator + fileName;
    }

    /**
     * Checks if the file and its directory specified by the `filePath` exists. If the directory does not exist,
     * it creates the necessary directories. If the file itself does not exist, it creates the file. Additionally,
     * it writes a default content to the newly created file.
     */
    public void checkAndCreateFile() {
        File file = new File(filePath);
        File directory = file.getParentFile();

        // Create directory if it doesn't exist
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Create file if it doesn't exist
        if (!file.exists()) {
            try {
                file.createNewFile();
                // Optionally write default content to the file
                try (PrintWriter writer = new PrintWriter(file)) {
                    writer.println("Default content if needed");
                }
            } catch (IOException e) {
                System.out.println("ERROR: Unable to create file: " + e.getMessage());
            }
        }
    }

    /**
     * Saves the tasks in the database to the output file and keeps track of it
     *
     * @param database current list of tasks stored
     */
    public void saveTasks(ArrayList<Task> database) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(this.filePath))) {
            for (Task task : database) {
                writer.println(task.toFileFormat());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads the task into a database and then returns the new database
     *
     * @return the new list of tasks after loading
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> database = new ArrayList<>();
        File file = new File(this.filePath);
        if (!file.exists() || file.length() == 0) {
            return database;
        }
        File directory = file.getParentFile();
        checkFile(file);
        checkDirectory(directory);
        loader(file, database);
        return database;
    }

    /**
     * Checks if the file provided exists so that the loading of the tasks can be done
     *
     * @param f File to load the tasks into
     */
    public void checkFile(File f) {
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    /**
     * Checks if the file provided exists so that the loading of tasks can be done to
     * the directory
     *
     * @param d File path provided for the directory
     */
    public void checkDirectory(File d) {
        if (!d.exists()) {
            d.mkdirs();
        }
    }

    /**
     * Loads the data collected from the user to the output file path
     *
     * @param f File to store the data provided by the user
     * @param database to keep track of the tasks provided by the user
     */
    private void loader(File f, ArrayList<Task> database) {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))) {
            String line = "";
            fileParser(f, line, reader, database);

        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    /**
     * Parses through the file and adds the tasks to the to do list in the output file
     *
     * @param f File path to have the output to do list
     * @param line each line in the output file to read one by one
     * @param reader reads the next line as it goes
     * @param database to keep track of the tasks provided by the user
     * @throws IOException if the reading of the file has an issue
     */
    private void fileParser(File f, String line, BufferedReader reader, ArrayList<Task> database)
            throws IOException {
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" \\| ");
            Task task = null;
            try {
                parserHandle(task, parts, database);
            } catch (BuzzException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    /**
     * Handles the different types of commands provided by the user to load the
     * tasks afterward into the output file
     *
     * @param task the current task being read by the program
     * @param parts breakdown of the text provided by the user
     * @param database to keep track of the tasks in the todo list currently
     * @throws BuzzException if any of the creation of the tasks lead to an error regarding
     *                       descriptions and formatting
     */
    private void parserHandle(Task task, String[] parts, ArrayList<Task> database) throws BuzzException {
        switch (parts[0]) {
        case "T":
            task = new Todo(parts[2]);
            break;
        case "D":
            task = new Deadline(parts[2], parts[3]);
            break;
        case "E":
            task = new Event(parts[2], parts[3], parts[4]);
            break;
        default:
            task = task;
            break;
        }
        if (task != null) {
            task.setDone(parts[1].equals("1")); // Set the task's done status
            database.add(task); // Add task to the database
        }
    }
}
