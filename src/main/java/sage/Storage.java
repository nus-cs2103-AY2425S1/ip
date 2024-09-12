package sage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sage.List.TaskList;
import sage.Task.DeadlineTask;
import sage.Task.EventTask;
import sage.Task.Task;
import sage.Task.ToDoTask;

/**
 * A Storage object to load/store the user's tasks.
 * */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from a file and returns them as a list.
     *
     * @return The list of loaded tasks.
     * @throws IOException   If an I/O error occurs.
     * @throws SageException If the file is corrupted or contains invalid data.
     */
    public List<Task> loadTasks() throws IOException, SageException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            assert file.exists() : "File is supposed to be created.";
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String entry = scanner.nextLine();
            String[] entryDetails = entry.split(" \\| ");

            Task task = getTask(entryDetails);
            tasks.add(task);
        }
        scanner.close();
        return tasks;
    }

    private static Task getTask(String[] entryDetails) throws SageException {
        String type = entryDetails[0];
        boolean isDone = entryDetails[1].equals("1");
        String description = entryDetails[2];

        Task task;
        switch (type) {
        case "T":
            task = new ToDoTask(description);
            break;
        case "D":
            String by = entryDetails[3];
            task = new DeadlineTask(description, by);
            break;
        case "E":
            String from = entryDetails[3];
            String to = entryDetails[4];
            task = new EventTask(description, from, to);
            break;
        default:
            throw new SageException("Corrupted file.");
        }
        task.setDone(isDone);
        return task;
    }

    /**
     * Saves the list of tasks to a file.
     *
     * @param tasksList The list of tasks to be saved.
     * @throws IOException If an I/O error occurs.
     */
    public void saveTasks(TaskList tasksList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasksList.getTasks()) {
            fw.write(task.toSave() + System.lineSeparator());
        }
        fw.close();
    }
}
