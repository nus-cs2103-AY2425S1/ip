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
                    String[] deadlineSplit = a.split(" \\(by: |\\)");

                    LocalDateTime datetime = LocalDateTime.parse(deadlineSplit[1],
                                             DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));


                    //see if the task has been done or not
                    if (Character.compare(tmp.charAt(4), 'X') == 0) {
                        arr.add(new Deadline(true, deadlineSplit[0], datetime));
                    } else {
                        arr.add(new Deadline(false, deadlineSplit[0], datetime));
                    }
                } else if (tmp.startsWith("[E]")) {
                    String details = tmp.split("] ", 2)[1];
                    //getting important values to create the Event
                    String[] eventSplit = details.split(" \\(from: | to: |\\)");

                    LocalDateTime fromInDatetimeFormat = LocalDateTime.parse(eventSplit[1], DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
                    LocalDateTime toInDatetimeFormat = LocalDateTime.parse(eventSplit[2], DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));

                    //see if the task has been done or not
                    if (Character.compare(tmp.charAt(4), 'X') == 0) {
                        arr.add(new Event(true, eventSplit[0], fromInDatetimeFormat, toInDatetimeFormat));
                    } else {
                        assert Character.compare(tmp.charAt(4), ' ') == 0 : "Error in file";
                        arr.add(new Event(false, eventSplit[0], fromInDatetimeFormat, toInDatetimeFormat));
                    }
                } else {
                    continue;
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
            String temporary = tmp.split("] ")[1];

            //get the important values to create the Deadline
            String[] deadlineDetails = temporary.split(" \\(by: |\\)");

            LocalDateTime datetime = LocalDateTime.parse(deadlineDetails[1],
                                     DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));

            //see if the task has been done or not
            if (Character.compare(tmp.charAt(4), 'X') == 0) {
                arr.add(new Deadline(true, deadlineDetails[0], datetime));
            } else {
                arr.add(new Deadline(false, deadlineDetails[0], datetime));
            }
        } else {
            //removing the [E][?] from the line
            String details = tmp.split("] ", 2)[1];
            //getting important values to create the Event
            String[] eventDetails = details.split(" \\(from: | to: |\\)");

            LocalDateTime fromDatetime = LocalDateTime.parse(eventDetails[1],
                                         DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
            LocalDateTime toDatetime = LocalDateTime.parse(eventDetails[1],
                                       DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
            //see if the task has been done or not
            if (Character.compare(tmp.charAt(4), 'X') == 0) {
                arr.add(new Event(true, eventDetails[0], fromDatetime, toDatetime));
            } else {
                arr.add(new Event(false, eventDetails[0], fromDatetime, toDatetime));
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
