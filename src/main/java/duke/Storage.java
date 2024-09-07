package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * This class handles the File I/O for storing and retrieving user's tasks.
 */
public class Storage {
    private final String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * The method that loads task information from the user to the program upon running the program.
     * @return an ArrayList holding in order, all the user's tasks.
     * @throws FileNotFoundException If the file storing the tasks of the user cannot be found, this error is thrown.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(this.filepath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<Task> userInputs = new ArrayList<>();
        while (s.hasNextLine()) {
            String string = s.nextLine();
            String[] items = string.split(" , ");
            String task = items[0];
            boolean flag = Objects.equals(items[1], "1");
            if (Objects.equals(task, "T")) {
                Todo taskToBeAdded = new Todo(items[2]);
                taskToBeAdded.setDone(flag);
                userInputs.add(taskToBeAdded);
            } else if (Objects.equals(task, "D")) {
                Deadline taskToBeAdded = new Deadline(items[2], LocalDate.parse(items[3]));
                taskToBeAdded.setDone(flag);
                userInputs.add(taskToBeAdded);
            } else if (Objects.equals(task, "E")) {
                String start = items[3].split("-", 2)[0];
                String end = items[3].split("-", 2)[1];
                Event taskToBeAdded = new Event(items[2], start, end);
                taskToBeAdded.setDone(flag);
                userInputs.add(taskToBeAdded);
            }
        }
        s.close();
        return userInputs;
    }

    /**
     * Writes the tasks from the program to the hard-disk for permanent storage, for when the program is re-run.
     * @param filePath The file path to the text file holding the previously listed tasks of the user.
     * @param tasks The object holding all the tasks of the user during the period when the program is run.
     * @throws IOException If there is an I/O error when the information is getting stored to hard-disk, this error
     *     is thrown.
     */
    public void writeToFile(String filePath, TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks.getTaskList()) {
            fw.write(task.getWriteFormat() + "\n");
        }
        fw.close();
    }
}
