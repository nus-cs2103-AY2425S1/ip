package sage;

import sage.List.TaskList;
import sage.Task.DeadlineTask;
import sage.Task.EventTask;
import sage.Task.Task;
import sage.Task.ToDoTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        List<Task> taskList = new ArrayList<>();
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

            String type = entryDetails[0];
            boolean isDone = entryDetails[1].equals("1");
            String description = entryDetails[2];

            switch (type) {
            case "T":
                Task toDo = new ToDoTask(description);
                toDo.setDone(isDone);
                taskList.add(toDo);
                break;
            case "D":
                String by = entryDetails[3];
                Task deadline = new DeadlineTask(description, by);
                deadline.setDone(isDone);
                taskList.add(deadline);
                break;
            case "E":
                String from = entryDetails[3];
                String to = entryDetails[4];
                Task event = new EventTask(description, from, to);
                event.setDone(isDone);
                taskList.add(event);
                break;
            default:
                throw new SageException("Corrupted file.");
            }
        }
        scanner.close();
        return taskList;
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
