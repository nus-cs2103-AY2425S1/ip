package Mario;

import Mario.Tasks.Deadline;
import Mario.Tasks.Event;
import Mario.Tasks.Task;
import Mario.Tasks.ToDo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final String DIRECTORY_PATH = "./data/";
    private static final String FILE_PATH = DIRECTORY_PATH + "mario.txt";

    private void ensureDirectoryExists() {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    /**
     * Writes formatted tasks into the mario.txt file.
     * @param tasks
     * @throws IOException
     */
    public void saveTasks(TaskList tasks) throws IOException {
        ensureDirectoryExists();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks.getAllTasks()) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        }
    }

    /**
     * Reads the tasks from the mario.txt file.
     * @return List of tasks.
     * @throws IOException
     */
    public List<Task> loadTasks() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return tasks; // Return an empty list if the file does not exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length >= 3) {
                    String type = parts[0];
                    boolean isCompleted = parts[1].equals("1");
                    String name = parts[2];

                    switch (type) {
                        case "T":
                            tasks.add(new ToDo(name, isCompleted));
                            break;
                        case "D":
                            String deadline = parts[3];
                            tasks.add(new Deadline(name, deadline, isCompleted));
                            break;
                        case "E":
                            String duration = parts[3];
                            String start = duration.split("-")[0];
                            String end = duration.split("-")[1];
                            tasks.add(new Event(name, start, end, isCompleted));
                            break;

                    }
                }
            }
        }
        return tasks;
    }



}
