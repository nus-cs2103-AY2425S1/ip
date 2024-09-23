package trackie.storage;

import trackie.parsing.Parser;
import trackie.tasks.Deadline;
import trackie.tasks.Event;
import trackie.tasks.Task;
import trackie.tasks.Todo;
import trackie.ui.TrackieException;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Represents a storage mechanism that can load data into <code>tasklist</code> and
 * save data to the <code>tasklist</code>.
 */
public class Storage {
    private TaskList taskList;
    private File fPtr;

    /**
     * Constructs a new Storage object.
     *
     * @param dirpath The path where the storage file will be created or accessed.
     * @param taskList The TaskList object to be associated with this storage.
     */
    public Storage(String dirpath, TaskList taskList) {
        try {
            String filename = "data.txt";
            fPtr = new File(dirpath);
            if (!fPtr.exists()) {
                fPtr.mkdirs();
            }
            fPtr = new File(dirpath + File.separatorChar + filename);
            fPtr.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating file =(");
        }

        this.taskList = taskList;
    }

    /**
     * Loads task data from the storage file into memory.
     * This method reads the file and populates the associated TaskList with the stored tasks.
     * After loading, it displays the list of loaded tasks.
     */
    public String load() {
        try {
            Scanner sc = new Scanner(fPtr);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                String[] arguments = s.split("\\|");

                // add tasks according to their types
                if (arguments[0].equals("T")) {
                    taskList.tasks.add(new Todo(arguments[2], Integer.parseInt(arguments[1])));
                } else if (arguments[0].equals("D")) {
                    taskList.tasks.add(new Deadline(arguments[2], arguments[3], Integer.parseInt(arguments[1])));
                } else if (arguments[0].equals("E")) {
                    taskList.tasks.add(new Event(arguments[2], arguments[3], arguments[4], Integer.parseInt(arguments[1])));
                }
            }

            if (taskList.isEmpty()) {
                return "You currently have no tasks =)";
            }

            String loadedList = Parser.parseCommand("list").execute(taskList, this);
            return "Here are your tasks: \n" + loadedList;

        } catch (FileNotFoundException e) {
            return "Error: File not found.";
        } catch (TrackieException ex) {
            return ex.getMessage();
        }
    }

    /**
     * Saves the current state of the TaskList to the storage file.
     * This method writes all tasks from the TaskList to the file, overwriting any existing content.
     * Each task is saved with its type, status, description, and any additional type-specific information.
     */
    public void save() {
        try {
            PrintWriter pw = new PrintWriter(fPtr);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (Task x : taskList.tasks) {
            String data = "";
            int status = x.getStatusIcon().equals("X") ? 1 : 0;
            if (x instanceof Todo) {
                data = String.format("T|%d|%s", status, x.getDescription());
            } else if (x instanceof Deadline) {
                Deadline d = (Deadline) x;
                data = String.format("D|%d|%s|%s", status, d.getDescription(), d.getDeadline());
            } else if (x instanceof Event) {
                Event e = (Event) x;
                data = String.format("E|%d|%s|%s|%s", status, e.getDescription(), e.getStartTime(), e.getEndTime());
            }

            try {
                FileWriter fw = new FileWriter(fPtr, true);
                fw.write(data);
                fw.write(System.lineSeparator());
                fw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
