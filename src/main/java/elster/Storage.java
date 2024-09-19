package elster;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import elster.tasks.DeadlineTask;
import elster.tasks.EventTask;
import elster.tasks.Task;
import elster.tasks.ToDoTask;

/**
 * Storage component of Elster, handles writing to and reading from the save file.
 */
public class Storage {
    private final Path dirPath;
    private final Path filePath;

    /**
     * Constructor of Storage, initialises the file path of the save file.
     *
     * @param filePath File path of the save file.
     */
    public Storage(Path filePath) {
        this.dirPath = filePath;
        this.filePath = dirPath.resolve("data.txt");
    }

    /**
     * Loads the task list from the file where it is saved.
     * In this case the save file is data/data.txt.
     *
     * @param taskList Task list where the tasks from the file are saved.
     * @throws Elseption If something goes wrong while reading the file.
     */
    public void loadFromFile(TaskList taskList) throws Elseption {
        try {
            ensureFileExists();

            File f = new File(filePath.toString());
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                Task task = null;
                String[] row = s.nextLine().split("\\|");
                String taskType = row[0].strip();
                boolean isDone = !row[1].strip().equals("0");
                String description = row[2].strip();

                task = getTask(taskType, description, row, isDone);

                assert task != null : "Save file should only store task data";

                if (task != null) {
                    taskList.addToList(task);
                }
            }

        } catch (IOException e) {
            throw new Elseption("    there hath been a failure in loading your work\n"
                    + "    your list starts empty");
        }
    }

    /**
     * Writes task list to a save file.
     * In this case the save file is data/data.txt.
     *
     * @param taskList Task list to be written to the save file.
     * @throws Elseption If something goes wrong while writing to file.
     */
    public void writeToFile(TaskList taskList) throws Elseption {
        try {
            ensureFileExists();

            assert Files.exists(dirPath) : "Directory does not exist";
            assert Files.exists(filePath) : "File does not exist";

            FileWriter fw = new FileWriter(filePath.toString());
            fw.write(taskList.fileString());
            fw.close();

        } catch (IOException e) {
            throw new Elseption("there hath been a failure in saving your work");
        }
    }

    private void ensureFileExists() throws IOException {
        if (Files.notExists(dirPath)) {
            Files.createDirectory(dirPath);
            Files.createFile(filePath);

        } else if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }
    }

    private Task getTask(String taskType, String description, String[] row, boolean isDone)
            throws Elseption {
        Task task = null;

        switch (taskType) {
        case "T":
            task = ToDoTask.of("todo " + description);
            break;
        case "D":
            task = DeadlineTask.of("deadline " + description + " /by " + row[3].strip());
            break;
        case "E":
            task = EventTask.of("event " + description + " /from " + row[3].strip() + " /to " + row[4].strip());
            break;
        default:
            assert false;
            break;
        }
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }
}
