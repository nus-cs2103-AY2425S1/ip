import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage  {
    private final String fileName;

    Storage(String fileName) {
        this.fileName = fileName;
    }

    ArrayList<String[]> load() throws AtreidesException {
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            ArrayList<String[]> list = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(" \\| ");
                list.add(words);
            }
            return list;
        } catch (FileNotFoundException e) {
            throw new AtreidesException(e.getMessage());
        }
    }

    void writeTasks(TaskList tasks) throws AtreidesException {
        try {
            PrintWriter pw = new PrintWriter(fileName);
            String taskString = tasks.writeList();
            pw.print(taskString);
            pw.flush();
            pw.close();
        } catch (FileNotFoundException e) {
            throw new AtreidesException(e.getMessage());
        }
    }
}
