package nuffle.storage;

import nuffle.task.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Handles loading and saving tasks to and from a file.
 * The file format is used to read and write tasks in a specific format.
 */
public class Storage {
    // variable to store the file path
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path.
     * The method reads the file line by line and creates appropriate Task objects based on the file's content.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File taskFile = new File(filePath);

        // check if the file exists. If it does not, make the directory
        if (!taskFile.exists()) {
            if (taskFile.getParentFile().mkdir()) {
                taskFile.createNewFile();
                return tasks;
            } else {
                // throw an error if there is issue creating directory
                throw new IOException("Problem creating the directory: " + taskFile.getParent());
            }
        }

        // if the file exists, need to buffer the content
        FileReader fr = new FileReader(filePath);
        BufferedReader buffer = new BufferedReader(fr);

        String eachLine = buffer.readLine();
        while (eachLine != null) {
            // dissect eachLine based on the expected format
            // for example: D | 0 | return book | June 6th, then split it by "|"
            String[] components = eachLine.split("\\|");

            String category = components[0].trim();
            String desc = components[2].trim();
            // note that D contains a fourth component and E contains a fourth and fifth component
            if (Objects.equals(category, "T")) {

                Todo task = new Todo(desc);
                if (components[1].trim().equals("1")) {
                    task.markAsDone();
                } else {
                    task.markNotDone();
                }
                tasks.add(task);
                eachLine = buffer.readLine();
            } else if (Objects.equals(category, "D")) {
                LocalDateTime by = LocalDateTime.parse(components[3].trim(), DateTimeFormatter.ofPattern("yyyy-MMM-dd HHmm"));
                Deadline deadline = new Deadline(desc, by);
                if (components[1].trim().equals("1")) {
                    deadline.markAsDone();
                } else {
                    deadline.markNotDone();
                }

                tasks.add(deadline);
                eachLine = buffer.readLine();
            } else if (Objects.equals(category, "E")) {
                LocalDateTime to = LocalDateTime.parse(components[3].trim(), DateTimeFormatter.ofPattern("yyyy-MMM-dd HHmm"));
                LocalDateTime from = LocalDateTime.parse(components[4].trim(), DateTimeFormatter.ofPattern("yyyy-MMM-dd HHmm"));
                Event event = new Event(desc, to, from);
                if (components[1].trim().equals("1")) {
                    event.markAsDone();
                } else {
                    event.markNotDone();
                }
                tasks.add(event);
                eachLine = buffer.readLine();
            } else if (Objects.equals(category, "L")) {
                System.out.println(components[5]);
                LocalDateTime dueDate = LocalDateTime.parse(components[5].trim(), DateTimeFormatter.ofPattern("yyyy-MMM-dd HHmm"));
                System.out.println("here2");
                double amount = Double.parseDouble(components[4].trim());
                System.out.println(amount);
                Loan loan = new Loan(components[2], components[3], amount, dueDate);
                if (components[1].trim().equals("1")) {
                    loan.markAsDone();
                } else {
                    loan.markNotDone();
                }

                tasks.add(loan);
                eachLine = buffer.readLine();


            }
        }
        // close the BufferedReader
        buffer.close();
        // return the task list
        return tasks;
    }

    /**
     * Saves the provided list of tasks to the file specified by the file path.
     * The method writes each task's formatted string representation to the file.
     *
     * @param tasks The list of tasks to be saved to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        FileWriter fileWrite = new FileWriter(filePath);
        BufferedWriter buffer = new BufferedWriter(fileWrite);
        for (Task eachTask : tasks) {
            String output = eachTask.printSaveFormat();
            System.out.println(output);
            buffer.write(output);
            buffer.newLine();
        }
        // close the buffer
        buffer.close();
    }

}
