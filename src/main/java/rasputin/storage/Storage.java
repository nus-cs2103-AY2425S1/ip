package rasputin.storage;

import rasputin.task.Task;
import rasputin.task.TaskList;
import rasputin.task.Todo;
import rasputin.task.Deadline;
import rasputin.task.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.ArrayList;

/**
 * Class to read a .txt file to load into an ArrayList and read from an ArrayList to write to a .txt file.
 * If the .txt file to be read is not found, create new file and write to it.
 *
 */
public class Storage {

    protected String filePath;
    protected File file;

    /**
     * Takes the filePath to open the file to be read and written to.
     *
     * @param filePath Path of file to be opened.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(this.filePath);
        if (file.isFile()) {
            System.out.println("Task file found.");
        } else {
            System.out.println("Task file not found, creating task file.");
            try {
                file.createNewFile();
                System.out.println("Task file created");
            } catch (IOException e) {
                System.out.println("Unable to create task file.");
            }
        }
    }

    /**
     * Reads from a .txt file to store an ArrayList of tasks.
     * If file cannot be found or read, prints an error message.
     *
     * @return ArrayList of tasks
     */
    public ArrayList<Task> readFile() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(this.file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split("\\|");
                switch (split[0]) {
                    case "T":
                        Todo todo = new Todo(split[2]);
                        if (split[1].equals("1")) {
                            todo.markAsDone();
                        }
                        tasks.add(todo);
                        break;
                    case "D":
                        Deadline deadline = new Deadline(split[2], split[3]);
                        if (split[1].equals("1")) {
                            deadline.markAsDone();
                        }
                        tasks.add(deadline);
                        break;
                    case "E":
                        Event event = new Event(split[2], split[3], split[4]);
                        if (split[1].equals("1")) {
                            event.markAsDone();
                        }
                        tasks.add(event);
                        break;
                    default:
                        break;
                }
            }
            fileReader.close();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Task file not found.");
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

        return tasks;
    }

    /**
     * Writes the tasks in the TaskList to the .txt file.
     * If writing to file is successfully, print a success message, else print error message.
     *
     * @param tasks List of tasks to write into file
     */
    public void writeFile(TaskList tasks) {
        try {
            FileWriter fileWriter = new FileWriter(this.file);
            fileWriter.write("");
            for (Task item : tasks.getTasks()) {
                fileWriter.append(item.toSaveFormat());
            }
            fileWriter.close();
            System.out.println("Written to file successfully.");
        } catch (IOException e) {
            System.out.println("ERROR! Could not write to file.");
        }
    }

}
