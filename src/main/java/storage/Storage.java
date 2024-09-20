package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

public class Storage {

    private File file;

    /**
     * Constructs a new {@code Storage} object with the specified file path.
     * The file path is used to initialize the {@code File} object where tasks will be read from
     * or written to.
     *
     * @param filepath the path to the file used for storing tasks
     */
    public Storage(String filepath) {
        File path = new File("data");
        path.mkdir();
        file = new File(filepath);
    }

    /**
     * Reads tasks from a file and returns them as an {@code TaskList} of {@code Task} objects.
     * The file is read line by line, and each line is parsed to create the appropriate task
     * based on its type (TODO, DEADLINE, or EVENT). If a line indicates that a task is marked as done,
     * the corresponding task is marked as completed. If the file does not exist, it is created.
     * In case of any errors during file operations, an error message is printed.
     *
     * @return an {@code TaskList} containing the tasks read from the file
     */
    public TaskList read() {
        try {
            System.out.println("____________________________________________________________\nRetrieving inputs ...");
            Scanner sc = new Scanner(file);
            TaskList tasks = new TaskList();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                switch (line.charAt(1)) {
                case 'T':
                    Todo t = new Todo(line.substring(7));
                    if (line.charAt(4) == 'X') {
                        t.markAsDone();
                    }
                    tasks.addTask(t);
                    break;
                case 'D':
                    String[] dtmp = line.split(" \\(by: ");
                    String by = dtmp[1].split("\\)")[0];
                    Deadline d = new Deadline(dtmp[0].substring(7), by);
                    if (line.charAt(4) == 'X') {
                        d.markAsDone();
                    }
                    tasks.addTask(d);
                    break;
                case 'E':
                    String[] etmp = line.split(" \\(from: ");
                    String[] tmp2 = etmp[1].split(" to: ");
                    String from = tmp2[0];
                    String to = tmp2[1].split("\\)")[0];
                    Event e = new Event(etmp[0].substring(7), from, to);
                    if (line.charAt(4) == 'X') {
                        e.markAsDone();
                    }
                    tasks.addTask(e);
                    break;
                default:
                    break;
                }

            }
            sc.close();


            return tasks;
        } catch (FileNotFoundException fnfe) {
            try {
                FileWriter fw = new FileWriter(file);
                fw.close();
            } catch (IOException ioe) {
                System.out.println("____________________________________________________________");
                System.out.println("  Error creating storage ...");
            }


        }
        return new TaskList();

    }

    /**
     * Writes the provided list of {@code Task} objects to a file.
     * Each task is converted to its raw string representation and written to the file,
     * with each task on a new line. If an {@code IOException} occurs during the writing process,
     * an error message is printed.
     *
     * @param tasks the list of {@code Task} objects to be written to the file
     */
    public void write(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(file);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(tasks.get(i).toRawString());
                sb.append(System.lineSeparator());
            }
            fw.write(sb.toString());
            fw.close();
            System.out.println("____________________________________________________________");
            System.out.println("  Data saved!");
        } catch (IOException ioe) {
            System.out.println("____________________________________________________________");
            System.out.println("  Error saving ...");
        }
    }


}
