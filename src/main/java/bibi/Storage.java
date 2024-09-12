package bibi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import bibi.task.Deadline;
import bibi.task.Event;
import bibi.task.Task;
import bibi.task.TaskList;
import bibi.task.ToDo;

/**
 * Represents an object that handles the modification of the save file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a new Storage instance that handles modification of the save file located in
     * the specified path.
     *
     * @param filePath the path to the save file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Adds all the tasks specified in the save file into the TaskList.
     *
     * @param tasks The TaskList which will be used to contain the tasks found in the save file.
     * @throws FileNotFoundException When the save file cannot be located in the specified path.
     */
    public void restoreTasks(TaskList tasks) throws FileNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("Save file not found");
        }

        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String taskLine = s.next();
            String input = s.nextLine();
            boolean isDone = input.strip().startsWith("[X]");
            switch (taskLine.charAt(1)) {
            case 'T':
                Task t = new ToDo(input.substring(4).strip());
                tasks.addToTaskList(t);

                if (isDone) {
                    t.markAsDone();
                }

                break;
            case 'D':
                String[] deadlineName = input.substring(4)
                                            .strip()
                                            .split(" \\(by: ");
                Task dl = new Deadline(deadlineName[0].stripIndent(),
                                    deadlineName[1].substring(0,
                                    deadlineName[1].length() - 1));
                tasks.addToTaskList(dl);

                if (isDone) {
                    dl.markAsDone();
                }

                break;
            case 'E':
                String[] eventName = input.substring(4).strip().split("\\(");
                String[] interval = eventName[1].split(" - ");
                Event e = new Event(eventName[0].stripIndent(),
                        interval[0],
                        interval[1].substring(0, interval[1].length() - 1));
                tasks.addToTaskList(e);

                if (isDone) {
                    e.markAsDone();
                }

                break;
            default:
                assert false : "Program should not run this branch (storage::restoreTasks)";
            }
        }
    }

    /**
     * Writes to the file the current Storage instance points to.
     *
     * @param tasks The list of tasks in the save file.
     * @throws IOException when unable to write or create the file.
     */
    public void writeToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (int i = 0; i < tasks.getTaskCount(); i++) {
            fw.write(String.format("%s\n", tasks.getTask(i).toString()));
        }

        fw.close();
    }

    /**
     * Creates a new directory to store the save file if it doesn't exist already.
     */
    public void initializeDataDirectory() {
        if (!Files.exists(Path.of(filePath).getParent())) {
            new File("data").mkdirs();
        }

        // Check if previous list exists
        Path filePath = Path.of(this.filePath);
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                System.out.println("Something went wrong during file creation");
            }
        }
    }
}
