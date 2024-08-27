import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import Exception.BobException;


public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws BobException {
        ArrayList<Task> tasks = new ArrayList<>();
        //DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");

                Task task;
                switch (taskType) {
                    case "T":
                        task = new Todo(parts[2]);
                        break;
                    case "D":
                        //LocalDate deadlineDate = LocalDate.parse(parts[3], dateFormatter);
                        //task = new Deadline(parts[2], deadlineDate);
                        task = new Deadline(parts[2], parts[3]);
                        break;
                    case "E":
                        task = new Event(parts[2], parts[3], parts[4]);
                        break;
                    default:
                        throw new BobException("Unknown task type");
                }

                if (isDone) {
                    task.mark();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new BobException("Failed to load tasks from file");
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws BobException {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.fileFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new BobException("Failed to save tasks to file");
        }
    }
}
