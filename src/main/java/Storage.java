import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class Storage {
    public List<Task> load() throws NixyException {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath
            if (!file.exists()) {
                return tasks;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String taskString = scanner.nextLine();
                Task task = TaskDecoder.parseTask(taskString);
                tasks.add(task);
            }
            scanner.close();
        } catch (IOException e) {
            throw new NixyException("BLAHH!!! Error loading tasks from file.");
        }
    }

    public void save(TaskList taskList) throws NixyException {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(filePath);
            Iterator<Task> taskIterator = tasks.iterator();
            for (int i = 0; taskIterator.hasNext(); i++) {
                Task task = taskIterator.next();
                fileWriter.write(String.format("%s%n", task.toDataString()));
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new NixyException("BLAHH!!! Error saving tasks to file.");
        }
    }
}
