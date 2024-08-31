package orion.chatbot;

import orion.exceptions.OrionException;
import orion.exceptions.OrionPathNotFoundException;
import orion.exceptions.OrionTaskDataException;
import orion.exceptions.OrionTaskListSaveException;

import orion.tasks.Deadline;
import orion.tasks.Event;
import orion.tasks.Task;
import orion.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.DateTimeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    public static final String DATA_PATHNAME = "./data/tasks.txt";

    public static final String DATA_FOLDER_PATHNAME = "./data";

    protected Storage() {

    }

    protected List<Task> loadTasks() throws OrionException {
        File taskList = new File(DATA_PATHNAME);
        Path path = Paths.get(DATA_PATHNAME);
        List<Task> tasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(taskList);

            while (s.hasNext()) {
                String task = s.nextLine();
                String[] parsed = task.split(",");
                switch (parsed[0]) {
                    case "todo":
                        if (parsed.length != 3) {
                            throw new OrionTaskDataException("Unrecognised todo task format");
                        } else {
                            boolean done = parsed[1].equals("T");
                            Task todo = new Todo(parsed[2], done);
                            tasks.add(todo);
                            break;
                        }
                    case "deadline":
                        if (parsed.length != 4) {
                            throw new OrionTaskDataException("Unrecognised deadline task format");
                        } else {
                            boolean done = parsed[1].equals("T");
                            LocalDate time = LocalDate.parse(parsed[3]);
                            Task deadline = new Deadline(parsed[2], done, time);
                            tasks.add(deadline);
                            break;
                        }
                    case "event":
                        if (parsed.length != 5) {
                            throw new OrionTaskDataException("Unrecognised event task format");
                        } else {
                            boolean done = parsed[1].equals("T");
                            LocalDate start = LocalDate.parse(parsed[3]);
                            LocalDate end = LocalDate.parse(parsed[4]);
                            Task event = new Event(parsed[2], done, start, end);
                            tasks.add(event);
                            break;
                        }
                    default:
                        // File corrupted
                        throw new OrionTaskDataException("Unrecognised task type");
                }
            }

            return tasks;

        } catch (FileNotFoundException e) {
            try {
                File dataDirectory = new File(DATA_FOLDER_PATHNAME);
                Path dir = Paths.get(DATA_FOLDER_PATHNAME);
                if (dataDirectory.exists() && dataDirectory.isDirectory()) {
                    Files.createFile(path);
                } else if (dataDirectory.exists()) {
                    Files.delete(dir);
                    Files.createDirectory(dir);
                    Files.createFile(path);
                } else {
                    Files.createDirectory(dir);
                    Files.createFile(path);
                }
                throw new OrionTaskDataException("Task list not found, new list created");
            } catch (IOException err) {
                throw new OrionPathNotFoundException("Task list cannot be created");
            }
        } catch (OrionTaskDataException | DateTimeException e) {
            try {
                Files.delete(path);
                Files.createFile(path);
                throw new OrionTaskDataException("Task list corrupted, new list created");
            } catch (IOException err) {
                throw new OrionPathNotFoundException("Task list cannot be created");
            }
        }
    }

    public void saveTasks(List<String> saveTaskDescriptions) throws OrionTaskListSaveException {
        try {
            FileWriter fw = new FileWriter(DATA_PATHNAME);
            for (String s : saveTaskDescriptions) {
                fw.write(s);
            }
            fw.close();
        } catch (IOException e) {
            throw new OrionTaskListSaveException("Task list cannot be saved");
        }
    }
}
