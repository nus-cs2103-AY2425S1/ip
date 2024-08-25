package duck.storage;

import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.data.task.Task;
import duck.data.task.ToDo;
import duck.data.task.Deadline;
import duck.data.task.Event;
import duck.data.task.TaskType;
import duck.util.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private final File file;
    public Storage(String filePath) throws DuckException {
        this.filePath = filePath;
        file = createFileIfDoesNotExist(filePath);

    }

    public File createFileIfDoesNotExist(String filePath) throws DuckException{
        try {
            File directory = getFileDirectory(filePath);
            if (!directory.isDirectory()) {
                directory.mkdir();
            }

            File f = new File(filePath);

            if (f.createNewFile()) {
                System.out.println("New file created under root path:\n"
                        + "./" + filePath);
            }

            return f;
        } catch (SecurityException e) {
            System.out.println("Error creating directory due to security Exception:\n"
                    + e.getMessage());

        } catch (IOException e) {
            throw new DuckException("Error creating file:\n"
                    + e.getMessage());
        }
        return null;
    }


    public void loadTasks(TaskList tasks) throws DuckException {
        try {
            Scanner sc = new Scanner(file);
            int lineNumber = 0;
            while (sc.hasNextLine()) {
                lineNumber++;
                String line = sc.nextLine();
                String[] words = line.split(" \\| ");
                Task task = null;
                switch (words[0]) {
                case "T":
                    if (hasCorrectFileFormat(words, TaskType.TODO)) {
                        task = new ToDo(
                                getTaskDoneBoolean(words[1]),
                                words[2]);
                    }
                    break;
                case "D":
                    if (hasCorrectFileFormat(words, TaskType.DEADLINE)) {
                        task = new Deadline(
                                getTaskDoneBoolean(words[1]),
                                words[2],
                                Utils.convertToDateTime(words[3]));
                    }
                    break;
                case "E":
                    if (hasCorrectFileFormat(words, TaskType.EVENT)) {
                        task = new Event(
                                getTaskDoneBoolean(words[1]),
                                words[2],
                                Utils.convertToDateTime(words[3]),
                                Utils.convertToDateTime(words[4]));
                    }
                    break;
                default:
                    continue;
                }
                if (task != null) {
                    tasks.add(task);
                } else {
                    System.out.println("Warning: Skipping invalid line " + lineNumber + ": " + line);
                }
            }
        } catch (FileNotFoundException e) {
            throw new DuckException("File not found: " + file.getPath());
        }
    }

    public void writeTasks(TaskList tasks) throws DuckException {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task task : tasks) {
                fw.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new DuckException("Error updating file:\n" + e.getMessage());
        }
    }

    public void appendTask(Task task) throws DuckException {
        try (FileWriter fw = new FileWriter(filePath, true)) {
            fw.write(task.toFileFormat() + System.lineSeparator());
        } catch (IOException e) {
            throw new DuckException("Error writing to file:\n" + e.getMessage());
        }
    }

    private File getFileDirectory(String filePath) {
        return new File(filePath.substring(0, filePath.lastIndexOf('/')));
    }

    private boolean hasCorrectFileFormat(String[] words, TaskType type) {
        try {
            switch (type) {
            case TODO:
                return words.length == 3
                        && hasCorrectDoneFormat(words[1])
                        && !words[2].isEmpty();
            case DEADLINE:
                if (words.length == 4) {
                    Utils.convertToDateTime(words[3]);
                    return hasCorrectDoneFormat(words[1]) && !words[2].isEmpty();
                }

            case EVENT:
                if (words.length == 5) {
                    ;
                    return hasCorrectDoneFormat(words[1])
                            && !words[2].isEmpty()
                            && Utils.convertToDateTime(words[3])
                            .isBefore(Utils.convertToDateTime(words[4]));
                }
            default:
                return false;
            }
        } catch (DuckException e) {
            return false;
        }
    }

    private boolean hasCorrectDoneFormat(String isDone) {
        return isDone.equals("0") || isDone.equals("1");
    }

    private boolean getTaskDoneBoolean(String isDone) throws DuckException {
        if (!hasCorrectDoneFormat(isDone)) {
            throw new DuckException("Invalid task status in file: " + isDone);
        }
        return isDone.equals("1");
    }
}
