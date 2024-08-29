import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class deals with loading and saving tasks from the file
 */
public class Storage {
    private final String path;

    public Storage(String path) {
        this.path = path;
    }

    /**
     * Loads the tasks from the file 
     * @return ArrayList<Task> of tasks from the file
     * @throws InvalidStorageFileException
     */
    public ArrayList<Task> load() throws InvalidStorageFileException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            File f = new File(this.path);
            if (!f.exists()) {
                f.createNewFile();
            }
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                tasks.add(Parser.parseStorageFileLine(line));
            }
            sc.close();

            return tasks;
        } catch (IOException e) {
            throw new InvalidStorageFileException();
        }
    }

    /**
     * Updates the tasks from an ArrayList of tasks
     * @param taskList ArrayList<Task> of tasks
     * @throws InvalidStorageFileException
     */
    public void update(ArrayList<Task> taskList) throws InvalidStorageFileException {
        try {
            StringBuilder s = new StringBuilder();
            for (Task t: taskList) {
                s.append(t + System.lineSeparator());
            }
            String tasks = s.toString();
            FileWriter fw = new FileWriter(this.path);
            fw.write(tasks);
            fw.close();
        } catch (IOException e) {
            throw new InvalidStorageFileException();
        }
    }
}