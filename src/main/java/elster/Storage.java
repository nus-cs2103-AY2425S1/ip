package elster;

import elster.tasks.DeadlineTask;
import elster.tasks.EventTask;
import elster.tasks.Task;
import elster.tasks.ToDoTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class Storage {
    Path dirPath;
    Path filePath;

    public Storage(Path filePath, Ui ui) {
        this.dirPath = filePath;
        this.filePath = dirPath.resolve("data.txt");
    }

    public void loadFromFile(TaskList taskList) throws Elseption {
        try {
            if (Files.notExists(dirPath)) {
                Files.createDirectory(dirPath);
                Files.createFile(filePath);
                return;

            } else if (Files.notExists(filePath)) {
                Files.createFile(filePath);
                return;
            }

            File f = new File(filePath.toString());
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                Task task = null;
                String[] row = s.nextLine().split("\\|");
                System.out.println(Arrays.toString(row));
                String taskType = row[0].strip();
                Boolean isDone = row[1].strip().equals("0") ? false : true;
                String description = row[2].strip();

                if (taskType.equals("T")) {
                    task = ToDoTask.of("todo " + description);

                } else if (taskType.equals("D")) {
                    task = DeadlineTask.of("deadline " + description + " /by " + row[3].strip());
                    System.out.println(description + " /by " + row[3].strip());

                } else if (taskType.equals("E")) {
                    task = EventTask.of("event " +description + " /from " + row[3].strip() +
                            " /to " + row[4].strip());
                    System.out.println(description + " /from " + row[3].strip() +
                            " /to " + row[4].strip());
                }

                if (task != null) {
                    taskList.addToList(task);
                    if (isDone) {
                        task.markAsDone();
                    }
                }

            }

        } catch (IOException e) {
            throw new Elseption();
        }
    }

    public void writeToFile(TaskList taskList) throws Elseption {
        try {
            if (Files.notExists(dirPath)) {
                Files.createDirectory(dirPath);
                Files.createFile(filePath);

            } else if (Files.notExists(filePath)) {
                Files.createFile(filePath);
            }

            FileWriter fw = new FileWriter(filePath.toString());
            fw.write(taskList.fileString());
            fw.close();

        } catch (IOException e) {
            throw new Elseption();
        }
    }
}
