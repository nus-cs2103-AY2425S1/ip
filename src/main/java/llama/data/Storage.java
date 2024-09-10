package llama.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Handles saving of tasks into file
 */
public class Storage {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    // CHECKSTYLE.OFF: AbbreviationAsWordInName
    private final String FILE_PATH = "./data/taskFile.txt";
    // CHECKSTYLE.ON: AbbreviationAsWordInName
    private File taskFile;

    /**
     * Loads all tasks from previously saved file. If saved file not found, creates a new file to save to.
     *
     * @return TaskList that has all the tasks loaded from previous saved file
     * @throws IOException if there is an error opening existing saved file
     */
    public TaskList load() throws IOException {
        taskFile = new File(FILE_PATH);
        File folder = new File("./data");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        taskFile.createNewFile();
        return loadTasksFromFile(taskFile);
    }

    private TaskList loadTasksFromFile(File file) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        TaskList taskList = new TaskList();

        while (s.hasNext()) {
            String taskString = s.nextLine();
            String[] arr = taskString.split("\\|");

            Task currentLoadedTask = null;
            boolean isDone = arr[1].equals("1");

            if (arr[0].equals("T")) {
                currentLoadedTask = new Todo(arr[2], isDone);
            } else if (arr[0].equals("D")) {
                LocalDateTime endTime = LocalDateTime.parse((arr[3]), FORMATTER);
                currentLoadedTask = new Deadline(arr[2], endTime, isDone);
            } else if (arr[0].equals("E")) {
                LocalDateTime startTime = LocalDateTime.parse(arr[3], FORMATTER);
                LocalDateTime endTime = LocalDateTime.parse(arr[4], FORMATTER);
                currentLoadedTask = new Event(arr[2], startTime, endTime, isDone);
            }

            if (currentLoadedTask != null) {
                taskList.loadTask(currentLoadedTask);
            }
        }
        s.close();
        return taskList;
    }

    /**
     * Saves tasks from TaskList into the file
     *
     * @param taskList tasks to save into file
     * @throws IOException if there is an error loading file
     */
    public void save(TaskList taskList) throws IOException {
        int numberOfTasks = taskList.getSize();
        BufferedWriter writer = new BufferedWriter(new FileWriter(taskFile));
        for (int i = 0; i < numberOfTasks; i++) {
            writer.write(taskList.getTask(i).getSaveTaskString());
            writer.newLine();
        }
        writer.close();
    }
}
