package alex;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import alex.task.Task;

/**
 * Deals with loading tasks from file and saving tasks to file.
 */
public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from file
     *
     * @return ArrayList of tasks read from the file
     * @throws FileNotFoundException If no file exists on the file path given.
     * @throws AlexException If there is an issue when converting text in the file to a Task object,
     * likely due to incorrect formatting of text in the file.
     */
    public ArrayList<Task> load() throws FileNotFoundException, AlexException {
        File f = new File(this.filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<Task> list = new ArrayList<>();

        while (s.hasNext()) {
            Scanner lineScanner = new Scanner(s.nextLine());
            String category = lineScanner.next();

            ArrayList<String> arrOfStr = new ArrayList<>();
            Task task = switch (category) {
                case "[T][" -> {
                    lineScanner.next();
                    yield Parser.makeTodoTask(lineScanner, arrOfStr, false);
                }
                case "[T][X]" -> Parser.makeTodoTask(lineScanner, arrOfStr, true);
                case "[D][" -> {
                    lineScanner.next();
                    yield Parser.makeDeadlineTask(lineScanner, arrOfStr, false);
                }
                case "[D][X]" -> Parser.makeDeadlineTask(lineScanner, arrOfStr, true);
                case "[E][" -> {
                    lineScanner.next();
                    yield Parser.makeEventTask(lineScanner, arrOfStr, false);
                }
                case "[E][X]" -> Parser.makeEventTask(lineScanner, arrOfStr, true);
                default -> new Task("", false);
            };
            list.add(task);
        }
        return list;
    }

    /**
     * Saves Tasks to file
     *
     * @param list ArrayList of Tasks to be written to file to save them.
     * @throws IOException If there are issues when trying to write text to file.
     */
    public void save(ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < list.size() - 1; i++) {
            fw.write(list.get(i).storageString() + System.lineSeparator());
        }
        fw.write(list.get(list.size() - 1).storageString());
        fw.close();
    }
}
