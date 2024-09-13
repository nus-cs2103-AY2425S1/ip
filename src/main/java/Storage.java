import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public ArrayList<Task> load() throws IOException, ParseException, InvalidCommandException {
        ArrayList<Task> tasks = new ArrayList<>();

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return tasks;
        }

        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] split = line.split(" \\| ");

            switch (split[0]) {
                case "T":
                    tasks.add(new TodoTask(split[2]));
                    break;
                case "D":
                    tasks.add(new DeadlineTask(split[2], split[3]));
                    break;
                case "E":
                    tasks.add(new EventTask(split[2], split[3], split[4]));
                    break;
                default:
                    throw new InvalidCommandException("Invalid task type: " + split[0]);
            }
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        PrintWriter printWriter = new PrintWriter(file);
        for (Task task : tasks) {
            printWriter.println(task.toText());
        }
        printWriter.close();
    }
}