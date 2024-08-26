package ip.derrick ;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class Storage {

    private static final String FOLDER_PATH = Paths.get(System.getProperty("user.home"), "ip","data").toString();
    private static final String FILE_NAME = "DATA.TXT";
    private static final Path FILE_PATH = Paths.get(FOLDER_PATH, FILE_NAME);
    public Storage (){
        createDirectory();
    }

    /**
     * Creates a new directory if the specified directory does not exist.
     * If the specified FILE_NAME does not exist at the specified directory, it also creates the file.
     */
    public void createDirectory() {
        Path folderPath = Paths.get(FOLDER_PATH);
        if (!java.nio.file.Files.exists(folderPath)) {
            try {
                Files.createDirectories(folderPath);
            } catch (IOException e) {
                System.out.println("An error occurred while creating the directory: " + e.getMessage());
            }
        }

        if (!Files.exists(FILE_PATH)) {
            try {
                Files.createFile(FILE_PATH);
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + e.getMessage());
            }
        }
    }

    /**
     * Loads tasks from a specified file.
     * If the file is empty, return an empty ArrayList.
     * @return ArrayList<Task> result.
     * @throws RuntimeException If an I/O error occurs during the retrieval of the directory or file.
     */
    public ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> result = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(FILE_PATH)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task = null;
                switch (type) {
                    case "T":
                        task = new Todo(description);
                        break;
                    case "D":
                        String by = parts[3];
                        task = new Deadline(description, by);
                        break;
                    case "E":
                        String start = parts[3];
                        String end = parts[4];
                        task = new Event(description, start, end);
                        break;
                }

                if (task != null) {
                    if (isDone) {
                        task.setMark();
                    }
                    result.add(task);
                }
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Update the specified file and save it to the specified directory and folder.
     * @param list The list to be updated and saved to a specified file.
     */
    public void saveTasksToFile(TaskList list) {
        try (BufferedWriter writer = Files.newBufferedWriter(FILE_PATH)) {
            for (Task task : list.output()) {
                writer.write(task.changeFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to file: " + e.getMessage());
        }
    }

}
