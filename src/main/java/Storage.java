import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static final String FILEPATH = "./data/luna.txt";

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(FILEPATH);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                String[] taskInfo = input.split("\\|");
                String taskType = taskInfo[0];
                int taskStatus = Integer.parseInt(taskInfo[1]);

                switch (taskType) {
                case "T":
                    Todo todo = new Todo(taskInfo[2]);
                    if (taskStatus == 1) {
                        todo.markAsDone();
                    }
                    tasks.add(todo);
                    break;

                case "D":
                    Deadline deadline = new Deadline(taskInfo[2], LocalDateTime.parse(taskInfo[3]));
                    if (taskStatus == 1) {
                        deadline.markAsDone();
                    }
                    tasks.add(deadline);
                    break;

                case "E":
                    Event event = new Event(taskInfo[2], LocalDateTime.parse(taskInfo[3]),
                            LocalDateTime.parse(taskInfo[4]));
                    if (taskStatus == 1) {
                        event.markAsDone();
                    }
                    tasks.add(event);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Create a new text file luna.txt in the data directory");
        } catch (Exception e) {
            System.out.println("Data file is corrupted or not in expected format");
        }
        return tasks;
    }

    public void saveTasks(ArrayList<Task> tasks) {
        try {
            File tempFile = new File("./data/temp.txt");
            FileWriter fw = new FileWriter(tempFile);

            for (Task task : tasks) {
                fw.write(task.toFileFormat() + System.lineSeparator());
            }

            fw.close();
            Path tempPath = Paths.get("./data/temp.txt");
            Files.copy(tempPath, Paths.get("./data/luna.txt"), StandardCopyOption.REPLACE_EXISTING);
            Files.delete(tempPath);

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
