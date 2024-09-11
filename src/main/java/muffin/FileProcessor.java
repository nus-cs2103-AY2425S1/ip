package muffin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Handled reading tasks from a file and writing tasks to a file.
 * It processes Todos, Deadlines, and Events by reading their data from a file and
 * saving the updated task list back to the file.
 */
public class FileProcessor {

    /**
     * Reads tasks from a file located at the specified file path. The file is
     * expected to have lines representing different types of tasks, each line
     * containing task details separated by a pipe '|' character.
     *
     * @param filePath The path of the file to read tasks from.
     * @return An ArrayList of tasks read from the file.
     */
    public ArrayList<Task> readFromFile(String filePath) {
        assert filePath != null;

        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);

            if (file.exists()) {
                List<String> lines = Files.readAllLines(Paths.get(filePath));

                for (String line: lines) {
                    String[] parts = line.split("\\|");

                    switch (parts[0]) {
                    case "T":
                        Task t = new Todo(parts[2]);
                        markStatus(parts[1], t);
                        tasks.add(t);
                        break;

                    case "D":
                        Task d = new Deadline(parts[2], parts[3]);
                        markStatus(parts[1], d);
                        tasks.add(d);
                        break;

                    case "E":
                        Task e = new Event(parts[2], parts[3], parts[4]);
                        markStatus(parts[1], e);
                        tasks.add(e);
                        break;

                    default:
                        break;
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    /**
     * Marks the task as done or not based on the provided status.
     *
     * @param input A string representing the status of the task. "1" means the task is done, and "0" means it's not.
     * @param task The task to update the status of.
     */
    public void markStatus(String input, Task task) {
        if (input.equals("1")) {
            task.isDone = true;
        }
    }

    /**
     * Writes the list of tasks to a file at the specified file path. The tasks are serialized
     * into lines of text, each line representing a task and its details.
     *
     * @param filePath The path of the file to write the tasks to.
     * @param tasks An ArrayList of tasks to write to the file.
     */
    public void writeToFile(String filePath, ArrayList<Task> tasks) {
        assert filePath != null;
        assert tasks != null;

        try {
            ArrayList<String> content = new ArrayList<>();
            for (Task task: tasks) {
                String s = "";
                if (task instanceof Todo) {
                    s = String.format("T|%s|%s", checkStatus(task), task.description);
                } else if (task instanceof Deadline d) {
                    s = String.format("D|%s|%s|%s", checkStatus(d), d.description, d.by);
                } else if (task instanceof Event e) {
                    s = String.format("E|%s|%s|%s|%s", checkStatus(e), e.description, e.from, e.to);
                }
                content.add(s);
            }
            Files.write(Paths.get(filePath), content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks the status of a task and returns "1" if the task is done, or "0" if it is not.
     *
     * @param task The task whose status is being checked.
     * @return A string "1" if the task is done, or "0" if it is not.
     */
    public String checkStatus(Task task) {
        if (task.isDone) {
            return "1";
        } else {
            return "0";
        }
    }
}
