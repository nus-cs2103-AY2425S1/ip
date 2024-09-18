package gallium.main;

import gallium.task.Deadline;
import gallium.task.Event;
import gallium.task.Task;
import gallium.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class is responsible to manage tasks.
 * It reads tasks from and writes tasks to a file.
 */
public class Storage {
    File filePath;
    Scanner scanner;
    TaskList taskList;

    /**
     * Constructs a Storage object and initializes the file for reading and writing
     * tasks.
     * Creates the directories and file if they do not exist.
     * 
     * @param filePathString The path to the file to store tasks.
     */
    public Storage(String filePathString) {
        try {
            assert filePathString != null && !filePathString.isEmpty() : "File path cannot be null or empty";
            File dir = new File(filePathString.split("/gallium.txt")[0]);
            dir.mkdirs();
            File f = new File(dir, "gallium.txt");
            f.createNewFile();
            this.filePath = new File(filePathString);
            assert this.filePath.exists() : "File should exist after being created";
            this.scanner = new Scanner(this.filePath);
        } catch (IOException e) {
            System.out.println("Error creating file:" + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file into an ArrayList of Task objects.
     * Parses the task descriptions and creates Task objects based on their
     * prefixes.
     * 
     * @param ui The UI instance used for printing out messages.
     * @return An ArrayList of Task objects.
     */
    public ArrayList<Task> load(Ui ui) {
        ArrayList<Task> taskArrayList = new ArrayList<Task>();
        this.taskList = new TaskList(taskArrayList);
        try {
            while (scanner.hasNextLine()) {
                String taskDesc = scanner.nextLine();
                assert taskDesc != null && !taskDesc.isEmpty() : "Task description cannot be null or empty";
                if (taskDesc.startsWith("[T]")) {
                    Todo todo = new Todo(taskDesc);
                    taskArrayList.add(todo);
                } else if (taskDesc.startsWith("[D]")) {
                    Deadline deadline = new Deadline(taskDesc);
                    taskArrayList.add(deadline);
                } else if (taskDesc.startsWith("[E]")) {
                    Event event = new Event(taskDesc);
                    taskArrayList.add(event);
                }
            }
        } catch (ParseException e) {
            ui.showWrongDateTimeFormat();
        }
        Task.count = taskArrayList.size() + 1;
        scanner.close();
        assert taskArrayList.size() >= 0 : "Task list size cannot be negative";
        return taskArrayList;
    }

    /**
     * Writes the current list of tasks to the file.
     * 
     * @param ui The UI instance used for showing error messages if an IOException
     *           occurs.
     */
    public void writeFile(Ui ui) {
        StringBuilder listStringBuilder = new StringBuilder();
        for (int i = 1; i < Task.count; i++) {
            Task task = taskList.getTask(i - 1);
            assert task != null : "Task cannot be null";
            listStringBuilder.append(task.toString()).append("\n");
        }
        String listString = listStringBuilder.toString();
        try {
            FileWriter fw = new FileWriter("./data/gallium.txt");
            fw.write(listString);
            fw.close();
        } catch (IOException e) {
            ui.showIOException(e);
        }
    }
}
