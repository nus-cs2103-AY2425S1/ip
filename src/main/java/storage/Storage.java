package storage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;
import exception.InputFormatException;

/**
 * The Storage class handles file operations for saving and loading tasks.
 * It provides methods to read from and write to a file, as well as parse task data.
 */
public class Storage {
    /** The file path where tasks are stored. */
    private final String filePath;
    /**
     * Constructs a new Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return An ArrayList of Task objects read from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public ArrayList<Task> load() throws IOException{
        try {
            ArrayList<String> stringList = readFile();
            return getTasksFromFileString(stringList);
        } catch (FileNotFoundException e ) {
            createFile();
            return new ArrayList<>();
        }
    }

    /**
     * Creates a new file at the specified file path if it doesn't exist.
     * Also creates any necessary parent directories.
     */
    private void createFile() {
        File f = new File(this.filePath);
        try {
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("An file error occurred while trying to create new data file");
            //e.printStackTrace();
        }
    }

    /**
     * Writes the given text to the file, overwriting any existing content.
     *
     * @param text The text to write to the file.
     */
    public void writeFile(String text) {
        try {
            FileWriter fw = new FileWriter(this.filePath); // create a FileWriter in append mode
            fw.write(text );
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Reads the contents of the file and returns them as an ArrayList of strings.
     *
     * @return An ArrayList of strings, each representing a line from the file.
     * @throws FileNotFoundException If the file is not found.
     */
    private ArrayList<String> readFile() throws FileNotFoundException {
        ArrayList<String> taskString = new ArrayList<>();
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            taskString.add(s.nextLine());
        }
        return taskString;
    }

    /**
     * Converts an ArrayList of task strings into an ArrayList of Task objects.
     *
     * @param taskString An ArrayList of strings, each representing a task.
     * @return An ArrayList of Task objects parsed from the input strings.
     * @throws IOException If there's an error parsing the task strings.
     */
    private ArrayList<Task> getTasksFromFileString(ArrayList<String> taskString) throws IOException{
        ArrayList<Task> arrayList = taskString.stream().map(str -> {
            try {
                return this.getTaskFromString(str);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return null;
            }
        }).collect(Collectors.toCollection(ArrayList<Task>::new));
        if (arrayList.contains(null)) {
            throw new IOException("Error parsing file");
        }
        return arrayList;
    }

    /**
     * Parses a single string into a Task object.
     *
     * @param str The string representation of a task.
     * @return A Task object parsed from the input string.
     * @throws IOException If there's an error parsing the task string.
     */
    private Task getTaskFromString(String str) throws IOException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            if (str.startsWith("T")) {
                String[] strings = str.split("[|]");
                Task tmp = new Todo(strings[2].trim());
                if (strings[1].equals("1")) tmp.markAsDone();
                return tmp;
            }
            if (str.startsWith("E")) {
                String[] strings = str.split("[|]");
                Task tmp = new Event(strings[2], LocalDateTime.parse(strings[3].trim(), formatter),LocalDateTime.parse(strings[4].trim(), formatter));
                if (strings[1].equals("1")) tmp.markAsDone();
                return tmp;
            }
            if (str.startsWith("D")) {
                String[] strings = str.split("[|]");
                Task tmp = new Deadline(strings[2], LocalDateTime.parse(strings[3].trim(), formatter));
                if (strings[1].equals("1")) tmp.markAsDone();
                return tmp;
            }
            else {
                throw new InputFormatException("");
            }
        }  catch (InputFormatException e) {
            throw new IOException("Trouble getting data from database :" + e.getMessage());
        }
    }
}
