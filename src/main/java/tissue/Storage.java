package tissue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import tissue.task.Deadline;
import tissue.task.Event;
import tissue.task.Task;
import tissue.task.ToDo;

/**
 * Storage class to handle all reading and writing of tasks to local disk.
 */
public class Storage {
    private final Path path;
    private final Path file;

    /**
     * Constructor to determine the name and file path to store and read from.
     */
    public Storage(String path, String fileName) {
        assert path != null : "Path cannot be null";
        assert fileName != null : "File name cannot be null";
        this.path = Paths.get(path);
        this.file = Paths.get(path + fileName);
    }

    /**
     * Saves a task to the file.
     *
     * @param task The task to save to the file.
     */
    public void save(Task task) {
        String parsedTask = parseTask(task);
        try {
            Files.createDirectories(path);
            Files.writeString(
                    file, parsedTask, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Loads the tasks from the file into an array.
     *
     * @return The tasks stored in an array.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file.toString()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String taskType = values[0].strip();
                switch (taskType) {
                case "T":
                    taskList.add(new ToDo(Integer.parseInt(values[1]), values[2]));
                    break;
                case "E":
                    taskList.add(
                            new Event(
                                    Integer.parseInt(values[1]),
                                    values[2].strip(),
                                    values[3].strip(),
                                    values[4].strip()));
                    break;

                case "D":
                    taskList.add(
                            new Deadline(
                                    Integer.parseInt(values[1]),
                                    values[2].strip(),
                                    values[3].strip()));
                    break;

                default:
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return taskList;
    }

    private String parseTask(Task task) {
        String temp = "";
        if (task instanceof ToDo) {
            temp = String.format("T,%s,%s\n", task.getIsDone() ? 1 : 0, task.getTask());

        } else if (task instanceof Event event) {
            temp =
                    String.format(
                            "E,%s,%s,%s,%s\n",
                            event.getIsDone() ? 1 : 0,
                            event.getTask(),
                            event.getFrom(),
                            event.getTo());
        } else if (task instanceof Deadline dl) {
            temp = String.format("D,%s,%s,%s\n", dl.getIsDone() ? 1 : 0, dl.getTask(), dl.getBy());
        }
        return temp;
    }

    /**
     * Deletes a task from the file.
     *
     * @param line The index of the task.
     */
    public void delete(int line) {
        try {
            List<String> lines = Files.readAllLines(file);
            if (line <= lines.size()) {
                lines.remove(line - 1);
                Files.write(file, lines);
                System.out.println("Line " + line + " deleted successfully.");
            } else {
                System.out.println("Line number out of range.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
