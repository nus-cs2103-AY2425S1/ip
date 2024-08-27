package strand;

import strand.Exceptions.StrandException;
import strand.Exceptions.StrandFileNotFoundException;
import strand.Tasks.Deadline;
import strand.Tasks.Event;
import strand.Tasks.Task;
import strand.Tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Task> load() throws StrandException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(this.filepath);
            if (file.exists()) {
                Scanner s = new Scanner(file); // create a Scanner using the File as the source
                while (s.hasNext()) {
                    String line = s.nextLine();
                    Task newTask = getTask(line);
                    tasks.add(newTask);
                }

            }
        } catch (IOException e) {
            throw new StrandFileNotFoundException();
        }
        return tasks;
    }

    private static Task getTask(String line) throws StrandException {
        String[] split = line.split(" \\| ");
        Task newTask = null;
        switch (split[0]) {
        case "T" -> {
            newTask = new Todo(split[2]);
            if (split[1].equals("1")) {
                newTask.markAsDone();
            }
        }
        case "D" -> newTask = new Deadline(split[2], split[3]);
        case "E" -> newTask = new Event(split[2], split[3], split[4]);
        }
        if (split[1].equals("1")) {
            assert newTask != null;
            newTask.markAsDone();
        }
        return newTask;
    }

    public void save(String listOfTasks) throws StrandException {
        try {
            File file = new File(this.filepath);
            if (!file.exists()) {
                File parentFile = file.getParentFile();
                if (parentFile != null && !parentFile.exists()) {
                    if (!parentFile.mkdir()) {
                        throw new IOException("Error creating parent file");
                    }
                }
                if (!file.createNewFile()) {
                    throw new IOException("Error creating data file");
                }

            }
            FileWriter fileWriter = new FileWriter(this.filepath);
            fileWriter.write(listOfTasks);
            fileWriter.close();
        } catch (IOException e) {
            throw new StrandFileNotFoundException();
        }
    }
}
