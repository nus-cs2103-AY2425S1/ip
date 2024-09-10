package joe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import joe.task.Deadline;
import joe.task.Event;
import joe.task.Task;
import joe.task.TaskList;
import joe.task.Todo;

/**
 * The {@code Storage} class handles the reading and writing of task data
 * to and from a file. It supports loading tasks from a file and saving tasks
 * to a file.
 */
public class Storage {
    /** The file path where tasks are stored. */
    private final String filePath;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     *
     * @param filePath The file path where task data is stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path and parses each line
     * into its corresponding task type.
     *
     * @return An {@code ArrayList} containing the list of processed tasks.
     * @throws JoeException When the file is not found or cannot be read.
     */
    public ArrayList<Task> loadData() throws JoeException {
        File file = new File(this.filePath);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
            ArrayList<Task> tasks = new ArrayList<>();
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] lineArr = line.split("\\|");
                assert lineArr.length >= 3 : "Invalid task format in file";
                String taskType = lineArr[0];
                boolean isDone = Boolean.parseBoolean(lineArr[1]);
                String taskDesc = lineArr[2];
                switch (taskType) {
                case "T" -> {
                    tasks.add(new Todo(taskDesc, isDone));
                }
                case "D" -> {
                    LocalDate deadlineBy = LocalDate.parse(lineArr[3]);
                    tasks.add(new Deadline(taskDesc, deadlineBy).setIsDone(isDone));
                }
                case "E" -> {
                    LocalDate eventFrom = LocalDate.parse(lineArr[3]);
                    LocalDate eventTo = LocalDate.parse(lineArr[4]);
                    tasks.add(new Event(taskDesc, eventFrom, eventTo).setIsDone(isDone));
                }
                default -> {
                    throw new JoeException("File is not in correct format");
                }
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new JoeException(e.getMessage());
        }
    }

    /**
     * Saves the given task list to the hard disk at the specified file path.
     *
     * @param taskList The {@code TaskList} to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void saveData(TaskList taskList) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));
        ArrayList<Task> taskArr = taskList.toArrayList();
        for (Task task : taskArr) {
            writer.write(task.serialize());
            writer.newLine();
        }
        writer.close();
    }
}
