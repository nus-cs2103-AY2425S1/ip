import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws DonkException{
        try {
            List<Task> tasks = readFile("./save.txt");
            return tasks;
        } catch(Exception e) {
            throw new DonkException("Couldn't find file");
        }
    }

    private static List<Task> readFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        List<Task> tasks = new ArrayList<>();
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] data = line.split("\\|");
            if (line.charAt(0) == 'T') {
                tasks.add(new ToDo(data[2]));
            } else if (line.charAt(0) == 'D') {
                tasks.add(new Deadline(data[2], data[3]));
            } else if (line.charAt(0) == 'E') {
                tasks.add(new Event(data[2], data[3], data[4]));
            }
            if (line.charAt(2) == '1') {
                tasks.get(tasks.size() - 1).markDone();
            }
        }
        return tasks;
    }

    public static void writeToFile(String filePath, TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.size(); i++) {
            fw.write(tasks.getTask(i).toFileSaveString() + "\n");
        }

        fw.close();
    }


}
