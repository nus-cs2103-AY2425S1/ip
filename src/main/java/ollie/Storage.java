package ollie;


import ollie.exception.CorruptFileException;
import ollie.exception.OllieException;
import ollie.task.Deadline;
import ollie.task.Event;
import ollie.task.Task;
import ollie.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Responsible for all interactions (read & write) with our file (i.e. database).
 */
public class Storage {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Read and parse the list of tasks from the text file specified by filepath.
     *
     * @return List of tasks.
     * @throws OllieException If file is not found or file is corrupted.
     */
    public ArrayList<Task> load() throws OllieException {
        try {
            File f = new File(filePath);
            f.createNewFile();
            Scanner s = new Scanner(f);
            ArrayList<Task> tasks = new ArrayList<>();
            parseFile(f, s, tasks);
            return tasks;
        } catch (IOException e) {
            throw new OllieException(e.getMessage());
        }
    }

    private void parseFile(File f, Scanner s, ArrayList tasks) throws CorruptFileException {
        try {
            while (s.hasNext()) {
                String input = s.nextLine();
                String[] splitString = input.split(" \\| ", 3); // Type | 1 | Content
                Task task;
                switch (splitString[0]) {
                case "D" -> {
                    // Save as deadline
                    String[] details = splitString[2].split(" \\| ", 2);
                    task = new Deadline(details[0], LocalDate.parse(details[1], formatter));
                }
                case "E" -> {
                    // Save as event
                    String[] details = splitString[2].split(" \\| ", 3);
                    task = new Event(details[0], LocalDate.parse(details[1], formatter), LocalDate.parse(details[2], formatter));
                }
                case "T" ->
                    // Save as todo
                        task = new Todo(splitString[2]);
                default -> throw new CorruptFileException(filePath);
                }
                assert (task != null);

                // Check for mark
                if (Integer.parseInt(splitString[1]) != 0) {
                    task.markAsDone();
                }

                tasks.add(task);
            }
        } catch (Exception e) {
            throw new CorruptFileException(filePath);
        }
    }

    /**
     * Write the list of tasks to the text file specified by filepath.
     *
     * @param tasks ArrayList of tasks
     * @throws OllieException If there are issues with writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws OllieException {
        assert(tasks != null);
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (Task t : tasks) {
                fw.write(t.getFormattedString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new OllieException(e.getMessage());
        }
    }
}
