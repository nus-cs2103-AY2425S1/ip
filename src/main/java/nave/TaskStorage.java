package nave;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * The {@code TaskStorage} class handles the storage and retrieval of tasks
 * from a file. It provides methods for loading tasks from a file, saving new
 * tasks to the file, and deleting tasks from the file.
 */
public class TaskStorage {
    private final String filePathString;
    private final Path filePath;

    /**
     * Constructs a {@code TaskStorage} object with the specified file path.
     *
     * @param filePathString the path to the file where tasks are stored
     */
    public TaskStorage(String filePathString) {
        this.filePathString = filePathString;
        this.filePath = Path.of(filePathString);
    }

    /**
     * Loads tasks from the file specified by {@code filePathString} and
     * adds them to the given {@code TaskList}.
     * <p>
     * The method reads the file line by line, parses the task information,
     * and creates appropriate {@code Task} objects (such as {@code Todo},
     * {@code Deadline}, or {@code Event}) based on the data in each line.
     * </p>
     *
     * @param list the {@code TaskList} to which the tasks will be added
     */
    public void onStart(TaskList list) {
        try {
            File loadFile = new File(filePathString);
            Scanner scanner = new Scanner(loadFile);
            while (scanner.hasNext()) {
                String currLine = scanner.nextLine();
                String[] split = currLine.split(",");
                switch (split.length) {
                case 1:
                    list.addTask(new Todo(split[0]));
                    break;
                case 2:
                    list.addTask(new Deadline(split[0], LocalDate.parse(split[1])));
                    break;
                case 3:
                    list.addTask(new Event(split[0], split[1], split[2]));
                    break;
                default:
                    return;
                }
            }
        } catch (FileNotFoundException e) {
            //Local storage file should be created beforehand at "./data/tasks.txt"
            if (!Files.isDirectory(Path.of("./data"))) {
                System.out.println("Directory './data' does not exist. Please create it first.");
                System.exit(0);
            } else {
                System.out.println("File './data/tasks.txt' does not exist. Please create it first.");
                System.exit(0);
            }
        }
    }

    /**
     * Appends the given content to the file specified by {@code filePathString}.
     * <p>
     * This method is used to save new task data to the file. The content is
     * added at the end of the file.
     * </p>
     *
     * @param content the content to be written to the file
     */
    public void saveToFile(String content) {
        try {
            FileWriter writer = new FileWriter(filePathString, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deletes the task at the specified position from the file.
     * <p>
     * This method reads the file, skips the line corresponding to the specified
     * task index, and writes the remaining lines to a temporary file. The
     * temporary file is then copied back to the original file, effectively
     * removing the task at the specified index.
     * </p>
     *
     * @param place the index of the task to be deleted
     */
    public void deleteFromFile(int place) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePathString));
            FileWriter writer = new FileWriter("./data/temp.txt");

            //Counts the lines to find task to delete
            int counter = 1;
            String currLine;
            while ((currLine = reader.readLine()) != null) {
                if (counter == place) {
                    //If is line to be deleted, it is not copied over
                    counter++;
                    continue;
                }
                //Else, copy the line to temp file
                writer.write(currLine + System.lineSeparator());
                counter++;
            }
            writer.close();
            //Copy temp file back to original file
            Files.copy(Path.of("./data/temp.txt"), filePath, REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("There was an error with the file input.");
        }
    }
}
