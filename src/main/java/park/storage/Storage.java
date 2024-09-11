package park.storage;

import park.exceptions.ParkException;
import park.task.Deadline;
import park.task.Event;
import park.task.Task;
import park.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private final String filePath;

    /**
     * Constructs a Storage object.
     *
     * @param filePath Path of the file where tasks are recorded.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the lists of tasks loaded from saved file.
     * Creates a new list if saved file does not exist or is empty.
     *
     * @return TaskList object representing saved list of tasks.
     * @throws ParkException If there is an error loading or creating the file.
     */
    public TaskList load() throws ParkException {
        File f = new File(filePath);
        if (!f.exists()) {
            try {
                File dir = f.getParentFile();
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                f.createNewFile();
            } catch (IOException e) {
                throw ParkException.createFileException();
            }
            return new TaskList();
        }
        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));
            TaskList tasklist = new TaskList();
            for (String line : lines) {
                    Task t = decode(line);
                    tasklist.add(t);
            }
            return tasklist;
        } catch (IOException e) {
            throw ParkException.loadFileException();
        }
    }

    /**
     * Adds a new task to the saved file.
     *
     * @param t Task to be added.
     * @throws ParkException If there is an error writing to the file.
     */
    public void append(Task t) throws ParkException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(t.encode() + "\n");
            fw.close();
        } catch (IOException e) {
            throw ParkException.writeFileException();
        }
    }

    /**
     * Changes a task in the saved file.
     *
     * @param oldLine Encoded line in file representing the task to be changed.
     * @param newLine Encoded line representing what the task is changed to.
     * @throws ParkException If there is an error writing to the file.
     */
    public void modify(String oldLine, String newLine) throws ParkException {
        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));
            List<String> modifiedLines = new ArrayList<String>();
            for (String line : lines) {
                if (line.equals(oldLine)) {
                    modifiedLines.add(newLine);
                } else {
                    modifiedLines.add(line);
                }
            }
            Files.write(Path.of(filePath), modifiedLines);
        } catch (IOException e) {
            throw ParkException.writeFileException();
        }
    }

    /**
     * Deletes a task in the saved file.
     *
     * @param t Task to be deleted.
     * @throws ParkException If there is an error writing to the file.
     */
    public void delete(Task t) throws ParkException {
        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));
            List<String> modifiedLines = new ArrayList<String>();
            for (String line : lines) {
                if (!line.equals(t.encode())) {
                    modifiedLines.add(line);
                }
            }
            Files.write(Path.of(filePath), modifiedLines);
        } catch (IOException e) {
            throw ParkException.writeFileException();
        }
    }

    /**
     * Reads the line in the saved file and returns it as a Task object.
     *
     * @param line Encoded line in the saved file.
     * @return Task object represented by line.
     * @throws ParkException If file is corrupted.
     */
    public Task decode(String line) throws ParkException {
        try {
            String[] s = line.split("/");
            switch (s[0]) {
            case "T":
                Task toDo = new ToDo(s[2]);
                if (s[1].equals("[X]")) {
                    toDo.mark();
                }
                return toDo;
            case "D":
                Task deadline = new Deadline(s[2], s[3]);
                if (s[1].equals("[X]")) {
                    deadline.mark();
                }
                return deadline;
            case "E":
                Task event = new Event(s[2], s[3], s[4]);
                if (s[1].equals("[X]")) {
                    event.mark();
                }
                return event;
            default:
                throw ParkException.fileCorruptedException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw ParkException.fileCorruptedException();
        }
    }
}
