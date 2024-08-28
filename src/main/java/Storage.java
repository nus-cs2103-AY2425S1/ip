import java.io.File;
import java.io.FileNotFoundException;
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
     */
    public ArrayList<Task> load() {
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
}