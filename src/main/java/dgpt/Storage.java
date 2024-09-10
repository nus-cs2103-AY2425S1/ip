package dgpt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dgpt.exception.DgptFileNotFoundException;
import dgpt.task.Deadline;
import dgpt.task.Event;
import dgpt.task.Task;
import dgpt.task.TaskList;
import dgpt.task.ToDo;



/**
 * The Storage class is responsible for handling reading from and writing to
 * the file system for the Dgpt application. It manages loading and saving
 * tasks from/to a specified file path.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The path of the file where tasks are stored or retrieved from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path. The file is expected
     * to contain tasks in a specific format. The method reads the file line by line,
     * parses the task data, and adds them to a list of tasks.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     * @throws DgptFileNotFoundException If the file cannot be found.
     */
    public List<Task> load() throws IOException, DgptFileNotFoundException {
        List<Task> res = new ArrayList<>();
        try {

            File f = new File(this.filePath);
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                String curr = s.nextLine();
                String[] parts = curr.split(" \\| ");

                switch (parts[0]) {
                case "T" -> {
                    ToDo i = new ToDo(parts[2]);
                    if (parts[1].equals("1")) {
                        i.mark();
                    }
                    res.add(i);
                }
                case "D" -> {
                    Deadline i = new Deadline(parts[2], parts[3]);
                    if (parts[1].equals("1")) {
                        i.mark();
                    }
                    res.add(i);
                }
                case "E" -> {
                    Event i = new Event(parts[2], parts[3], parts[4]);
                    if (parts[1].equals("1")) {
                        i.mark();
                    }
                    res.add(i);
                }
                default -> {
                    throw new IOException("File format is invalid");
                }
                }
            }
        } catch (FileNotFoundException e) {
            throw new DgptFileNotFoundException("Could not find existing data");
        }

        return res;
    }

    /**
     * Saves the provided task list to the file specified by the file path. The method
     * writes each task to the file in a specific format. If the parent directory of
     * the file does not exist, it will be created.
     *
     * @param taskList The list of tasks to be saved to the file.
     * @throws IOException If an I/O error occurs while writing to the file or if
     *         the parent directory cannot be created.
     */
    public void save(TaskList taskList) throws IOException {
        assert taskList != null : "taskList cannot be null";
        File file = new File(this.filePath);

        // Handle the case where parent directory does not exist
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                throw new IOException("Failed to create directory: " + parentDir.getAbsolutePath());
            }
        }

        try {
            FileWriter fw = new FileWriter(filePath);
            StringBuilder s = new StringBuilder();

            for (Task t : taskList.getTaskList()) {
                if (t instanceof ToDo) {
                    s.append("T | ")
                            .append(t.getIsDone() ? "1 | " : "0 | ")
                            .append(t.getDescription())
                            .append("\n");
                } else if (t instanceof Deadline) {
                    s.append("D | ")
                            .append(t.getIsDone() ? "1 | " : "0 | ")
                            .append(t.getDescription())
                            .append(" | ")
                            .append(((Deadline) t).getDueDateString())
                            .append("\n");
                } else if (t instanceof Event) {
                    s.append("E | ")
                            .append(t.getIsDone() ? "1 | " : "0 | ")
                            .append(t.getDescription())
                            .append(" | ")
                            .append(((Event) t).getFromTime())
                            .append(" | ")
                            .append(((Event) t).getToTime())
                            .append("\n");
                }
            }

            fw.write(s.toString());
            fw.close();
        } catch (IOException e) {
            throw new IOException("An error occurred while writing to the file: " + e.getMessage(), e);
        }
    }
}
