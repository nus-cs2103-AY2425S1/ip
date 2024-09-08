package chacha;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import chacha.task.DeadlineTask;
import chacha.task.EventTask;
import chacha.task.Task;
import chacha.task.TaskList;
import chacha.task.ToDoTask;

/**
 * Handles the loading of and saving into chacha.txt.
 *
 */
public class Storage {
    protected String filePath;
    private BufferedReader readerFile;
    private FileWriter writtenFile;

    /**
     * Creates a Storage object with specified filePath.
     *
     * @param filePath
     * @throws IOException
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;

        File taskFile = new File(String.valueOf(filePath));
        this.readerFile = new BufferedReader(new FileReader(taskFile));
        this.writtenFile = new FileWriter(taskFile, true);
    }

    /**
     * Loads the list of tasks saved in chacha.txt.
     * Returns them as the list of <code>Task</code> as an <code>ArrayList</code>.
     *
     * @return ArrayList of Task.
     * @throws IOException if an I/O exception occurs.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        try {
            String line;

            while ((line = this.readerFile.readLine()) != null) {
                String[] arr = line.split(" \\| ");
                boolean isDone = (arr[1].equals("1"));

                if (arr[0].equals("T")) {
                    listOfTasks.add(new ToDoTask(arr[2], isDone));

                } else if (arr[0].equals("D")) {
                    listOfTasks.add(new DeadlineTask(arr[2], isDone, LocalDate.parse(arr[3])));

                } else if (arr[0].equals("E")) {
                    String[] timings = arr[4].split("-");
                    String startTime = timings[0];
                    String endTime = timings[1];
                    listOfTasks.add(new EventTask(arr[2], isDone, LocalDate.parse(arr[3]), startTime, endTime));
                }
            }
        } finally {
            this.readerFile.close();
        }

        return listOfTasks;
    }

    /**
     * Writes text into chacha.txt.
     *
     * @param text String that needs to be written.
     */
    public void writeFile(String text) {
        try {
            this.writtenFile.append(text);
            this.writtenFile.flush();

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    /**
     * Overwrites the content in chacha.txt with new list of tasks.
     *
     * @param tasks List of tasks.
     */
    public void overwriteFile(TaskList tasks) {
        try {
            File taskFile = new File(filePath);
            FileWriter overwrittenFile = new FileWriter(taskFile);

            for (int j = 0; j < tasks.getTotalNumber(); j++) {
                Task task = tasks.getTask(j);
                overwrittenFile.write(task.writeTask());
            }
            overwrittenFile.flush();

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
