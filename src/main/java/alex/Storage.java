package alex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import alex.task.Task;

/**
 * Handles loading tasks from a file and saving tasks to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be stored or retrieved.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path.
     *
     * @return An ArrayList of tasks read from the file.
     * @throws FileNotFoundException If the file specified by the file path does not exist.
     * @throws AlexException If there is an issue converting text in the file into a Task object,
     *                       likely due to incorrect formatting in the file.
     */
    public ArrayList<Task> load() throws FileNotFoundException, AlexException {
        File f = new File(this.filePath); // Create a File object for the given file path
        Scanner s = new Scanner(f); // Create a Scanner using the File as the source
        ArrayList<Task> list = new ArrayList<>();

        while (s.hasNext()) {
            String lineOfWords = s.nextLine();
            assert !lineOfWords.isEmpty() : "Empty line in Alex.txt";
            Scanner lineScanner = new Scanner(lineOfWords);
            String category = lineScanner.next();
            Task task = new Task("", false);

            ArrayList<String> arrOfStr = new ArrayList<>();
            switch (category) {
            case "[T][":
                lineScanner.next();
                task = Parser.makeTodoTask(lineScanner, arrOfStr, false);
                break;
            case "[T][X]":
                task = Parser.makeTodoTask(lineScanner, arrOfStr, true);
                break;
            case "[D][":
                lineScanner.next();
                task = Parser.makeDeadlineTask(lineScanner, arrOfStr, false);
                break;
            case "[D][X]":
                task = Parser.makeDeadlineTask(lineScanner, arrOfStr, true);
                break;
            case "[E][":
                lineScanner.next();
                task = Parser.makeEventTask(lineScanner, arrOfStr, false);
                break;
            case "[E][X]":
                Parser.makeEventTask(lineScanner, arrOfStr, true);
                break;
            default:
                //do nothing
            }
            list.add(task);
        }
        return list;
    }

    /**
     * Saves tasks to the file specified by the file path.
     *
     * @param list An ArrayList of Tasks to be written to the file.
     * @throws IOException If there are issues writing text to the file.
     */
    public void save(ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < list.size() - 1; i++) {
            fw.write(list.get(i).toStorageString() + System.lineSeparator());
        }
        fw.write(list.get(list.size() - 1).toStorageString());
        fw.close();
    }
}

