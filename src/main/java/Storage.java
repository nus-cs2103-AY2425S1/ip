import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Handles reading and writing TaskLog to file
 */
public class Storage {
    private static final Path STORAGE_PATH = Paths.get( "taskStorage", "taskLog.tsv");
    private File file;

    Storage() {
        File file = new File(STORAGE_PATH.toString());
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.isFile()) {
            createFile(file);
        }
        this.file = file;
    }

    public TaskLog getTasks() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        TaskLog taskLog = new TaskLog();
        while (scanner.hasNextLine()) {
            String taskTsv = scanner.nextLine();
            Task newTask = tsvToTask(taskTsv);
            taskLog.addTask(newTask);
        }
        scanner.close();
        return taskLog;
    }

    public void storeTasks(TaskLog taskLog) {
        try {
            FileWriter fw = new FileWriter(STORAGE_PATH.toString());
            for (Task task : taskLog.getLog()) {
                fw.write(task.toTsv());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to write to storage file");
            System.out.println(e.getMessage());
        }

    }

    private static void createFile(File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            // don't create it
            System.out.println("Cannot create storage file");
            System.out.println(e.getMessage());
        }
    }

    private Task tsvToTask(String tsv) {
        String[] tsvFields = tsv.split("\t");
        Task newTask;
        switch (tsvFields[0]) {
        case "ToDo":
            boolean todoIsDone = Boolean.parseBoolean(tsvFields[1]);
            String todoDesc = tsvFields[2];
            newTask = new ToDo(todoDesc, todoIsDone);
            break;
        case "Deadline":
            boolean deadlineIsDone = Boolean.parseBoolean(tsvFields[1]);
            String deadlineDesc = tsvFields[2];
            String deadlineTime = tsvFields[3];
            newTask = new Deadline(deadlineDesc, deadlineTime, deadlineIsDone);
            break;
        case "Event":
            boolean eventIsDone = Boolean.parseBoolean(tsvFields[1]);
            String eventDesc = tsvFields[2];
            String eventStart = tsvFields[3];
            String eventEnd = tsvFields[4];
            newTask = new Event(eventDesc, eventStart, eventEnd, eventIsDone);
            break;
        default:
            newTask = new ToDo("nullTask");
        }
        return newTask;
    }


}
