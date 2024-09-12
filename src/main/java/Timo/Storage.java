package Timo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Storage that loads data from list text file, and stores data into the file
 */
public class Storage {
    private final String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads tasks from a file specified by the `filepath` and returns them as a list of `Task` objects.
     * <p>
     * The tasks in the file are expected to be in a specific format, indicating the type of task
     * (Todo, Deadline, or Event) and its completion status.
     * </p>
     *
     * @return A `List` of `Task` objects representing the tasks stored in the file.
     *
     * @throws FileNotFoundException if the file at the specified `filepath` does not exist.
     *
     * @see Task
     * @see Todo
     * @see Deadline
     * @see Event
     */
    public List<Task> load() throws FileNotFoundException {
        File f = new File(this.filepath);

        //initialise array to store the values
        List<Task> arr = new ArrayList<Task>();

        //check if the file exists
        if (f.exists()) {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String tmp = s.nextLine();
                if (tmp.startsWith("[T]")) {
                    String[] a = tmp.split("] ", 2);
                    if (Character.compare(tmp.charAt(4), 'X') == 0) {
                        arr.add(new Todo(true, a[1]));
                    } else {
                        arr.add(new Todo(false, a[1]));
                    }
                } else if (tmp.startsWith("[D]")) {
                    //remove the [D][?] from the line
                    String a = tmp.split("] ")[1];

                    //get the important values to create the Deadline
                    String[] b = a.split(" \\(by: |\\)");

                    LocalDateTime datetime = LocalDateTime.parse(b[1], DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));


                    //see if the task has been done or not
                    if (Character.compare(tmp.charAt(4), 'X') == 0) {
                        arr.add(new Deadline(true, b[0], datetime));
                    } else {
                        arr.add(new Deadline(false, b[0], datetime));
                    }
                } else {
                    assert tmp.startsWith("[E]") : "Error in program";
                    String details = tmp.split("] ", 2)[1];
                    //getting important values to create the Event
                    String[] split_up = details.split(" \\(from: | to: |\\)");

                    //see if the task has been done or not
                    if (Character.compare(tmp.charAt(4), 'X') == 0) {
                        arr.add(new Event(true, split_up[0], split_up[1], split_up[2]));
                    } else {
                        assert Character.compare(tmp.charAt(4), ' ') == 0 : "Error in file";
                        arr.add(new Event(false, split_up[0], split_up[1], split_up[2]));
                    }
                }
            }
            return arr;
        } else {
            throw new FileNotFoundException("file not found!");
        }

    }

    /**
     * create the relevant Task and add it to the array
     * @param tmp the Task read from one line in text file
     * @param arr the array that will store all tasks
     */
    public void addTaskToArray(String tmp, List<Task> arr) {
        if (tmp.startsWith("[T]")) {
            String[] a = tmp.split("] ", 2);
            if (Character.compare(tmp.charAt(4), 'X') == 0) {
                arr.add(new Todo(true, a[1]));
            } else {
                arr.add(new Todo(false, a[1]));
            }
        } else if (tmp.startsWith("[D]")) {
            //remove the [D][?] from the line
            String a = tmp.split("] ")[1];

            //get the important values to create the Deadline
            String[] b = a.split(" \\(by: |\\)");

            LocalDateTime datetime = LocalDateTime.parse(b[1], DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));

            //see if the task has been done or not
            if (Character.compare(tmp.charAt(4), 'X') == 0) {
                arr.add(new Deadline(true, b[0], datetime));
            } else {
                arr.add(new Deadline(false, b[0], datetime));
            }
        } else {
            //removing the [E][?] from the line
            String details = tmp.split("] ", 2)[1];
            //getting important values to create the Event
            String[] split_up = details.split(" \\(from: | to: |\\)");

            //see if the task has been done or not
            if (Character.compare(tmp.charAt(4), 'X') == 0) {
                arr.add(new Event(true, split_up[0], split_up[1], split_up[2]));
            } else {
                arr.add(new Event(false, split_up[0], split_up[1], split_up[2]));
            }
        }
    }

    /**
     * Stores a list of tasks to a file specified by the `filepath`.
     * <p>
     * If the file does not exist, it will be created. The method overwrites any existing content
     * in the file and writes each task in the provided list to the file, with each task on a new line.
     * </p>
     *
     * @param arr A `List` of `Task` objects to be stored in the file.
     *
     * @see Task
     */
    public void store(List<Task> arr) {
        //create new file if file does not exist
        File file = new File(this.filepath);


        try {
            boolean filecreated = file.createNewFile();

            //delete all contents in the file
            FileWriter fil = new FileWriter(this.filepath);
            fil.write("");
            fil.close();


            //create FileWriter to append to file
            FileWriter fw = new FileWriter(this.filepath, true);
            for (Task i: arr) {
                fw.write(i + "\n");
            }
            fw.close();

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
