package echo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * The Storage class handles loading tasks from a file and saving tasks to a file.
 */
public class Storage {
    String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path of the file to load and save tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }




    /**
     * Loads tasks from the file specified by the file path and returns them as a list.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath); //always using same file

        if (file.exists()) {
            Scanner saveScanner = new Scanner(file);
            while (saveScanner.hasNextLine()) {
                String input = saveScanner.nextLine();
                String[] parts = input.split("\\|");

                String type = parts[0];
                String marked = parts[1];
                String des = parts[2];
                String info = parts[3];
                switch (type) {
                    case "T":
                        Todo todo = new Todo(des);
                        if (Objects.equals(marked, "1")) {
                            todo.setDone();
                        }
                        tasks.add(todo);
                        break;
                    case "D":
                        String[] details = input.split("/by ", 2);
                        if (details.length == 2) {
                            Deadline deadlineTask = new Deadline(des, details[1]);
                            if (Objects.equals(marked, "1")) {
                                deadlineTask.setDone();
                            }
                            tasks.add(deadlineTask);
                        }
                        break;
                    case "E":
                        String[] eventDetails = input.split(" /from ", 2);
                        if (eventDetails.length == 2) {
                            String[] times = eventDetails[1].split(" /to ", 2);
                            if (times.length == 2) {
                                Events eventTask = new Events(des, times[0], times[1]);
                                if (Objects.equals(marked, "1")) {
                                    eventTask.setDone();
                                }
                                tasks.add(eventTask);

                            }
                        }
                        break;
                    default:
                        System.out.println("Unknown task type: " + type);
                        break;
                }

            }
        }
        return tasks;
    }

    /**
     * Saves the list of tasks to the file specified by the file path.
     *
     * @param tasks The list of tasks to save.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void save(List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(TaskList.listToString(tasks));
        fw.close();
    }
}
