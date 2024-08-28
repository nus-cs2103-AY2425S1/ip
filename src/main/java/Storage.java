import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String PATH;
    private final String FILENAME = "/tasks.txt";

    public Storage(String path) {
        this.PATH = path;
    }

    public ArrayList<Task> load() throws AstraException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(PATH + FILENAME);
        try {
            Scanner inp = new Scanner(f);
            while (inp.hasNextLine()) {
                String line = inp.nextLine();
                tasks.add(Task.fromText(line));
            }
            inp.close();
        } catch (FileNotFoundException ignored) {
        } catch (Exception e) {
            throw new AstraException("Data file corrupted, failed to read all tasks.");
        }

        return tasks;
    }

    public void save(TaskList tasks) {
        String text = tasks.toText();

        try {
            File dir = new File(PATH);
            dir.mkdirs();
            FileWriter fw = new FileWriter(PATH + FILENAME);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
