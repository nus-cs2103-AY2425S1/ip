import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Deals with loading tasks from the data file and saving tasks in the data file
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates an empty data file if it does not already exist.
     *
     */
    public void initialise() {
        Path dataPath = Paths.get(this.filePath);
        boolean fileExists = Files.exists(dataPath);
        if (!fileExists) {
            Path directoryPath = Paths.get("data");
            try {
                Files.createDirectory(directoryPath);
                Files.createFile(dataPath);
            } catch (IOException e) {
                System.out.println("\tError occurred when creating directory/file.");
            }
        }
    }

    /**
     * Loads data from the oliver.txt data file.
     *
     * @return arraylist containing tasks saved in the data file
     * @throws IOException if tasks could not be read from the data file
     */
    public ArrayList<Task> loadData() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(this.filePath));

        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            String[] data = line.split("\\|");
            Task t;
            if (data[0].equals("T")) {
                t = new ToDo(data[2]);
            } else if (data[0].equals("D")) {
                t = new Deadline(data[2], LocalDateTime.parse(data[3]));
            } else {
                t = new Event(data[2], LocalDateTime.parse(data[3]), LocalDateTime.parse(data[4]));
            }
            if (data[1].equals("X")) {
                t.markAsDone();
            }
            tasks.add(t);
        }
        return tasks;
    }

    /**
     * Writes tasks to the data file.
     *
     * @param list the list of tasks to write to the data file
     */
    public void writeToFile(TaskList list) {
        ArrayList<Task> taskList = list.getList();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath, false));
            for (Task task : taskList) {
                writer.write(task.getFormatted());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("\tError occurred when writing to the data file.");
        }
    }
}
