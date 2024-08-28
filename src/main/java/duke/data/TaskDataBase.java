package duke.data;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.Event;
import duke.tasks.Deadline;
import duke.exceptions.InvalidDateException;
import duke.ui.Ui;

public class TaskDataBase {

    /**
     * Returns a List of task that is within file tasklist.txt
     *
     * @return a list of Task.
     * @throws IOException when error reading from file
     */
    public static List<Task> load() throws IOException, InvalidDateException {
        List<Task> tasks = new ArrayList<>();
        File file = new File("data/tasklist.txt");

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\|");
            Task task;

            switch (parts[0]) {
            case "T":
                task = new Todo(parts[2]);
                break;
            case "D":
                task = new Deadline(parts[2], parts[3]);
                break;
            case "E":
                 task = new Event(parts[2], parts[3], parts[4]);
                 break;
            default:
                throw new IllegalStateException("Unexpected task type: " + parts[0]);
            }

            if (parts[1].equals("1")) {
                task.markAsDone();
            }

            tasks.add(task);
        }

        reader.close();
        return tasks;
    }

    /**
     * Saves a list to tasklist.txt
     *
     * @param tasks a list of Task.
     * @throws IOException when error reading from file
     */
    public static void save(List<Task> tasks, Ui ui) {
        File file = new File("data/tasklist.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (Task task : tasks) {
                writer.write(task.toDataFormat());
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            ui.printMessage("Oops! There is a issue with file database.");
        }
    }

}

