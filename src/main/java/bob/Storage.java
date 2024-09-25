package bob;

import bob.exception.FileCorruptedException;
import bob.exception.LineCorruptedException;
import bob.exception.WrongTaskException;
import bob.task.*;
import bob.util.ClassGetter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * The Storage class handles storing and reading data from files.
 * The formats for encoding and decoding is defined within each task type.
 */
public class Storage {
    private final File file;
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("ddMMuuuuHHmm");
    private Class<? extends Task>[] taskClasses;

    /**
     * Constructs a Storage instance that stores and reads files at the given file path,
     * and loads task classes from the {@code bob.task} package.
     *
     * @param filePath where this instance stores and reads data from
     */
    public Storage(String filePath) {
        file = new File(filePath);
        this.loadTasks();
    }

    private void createFile() throws IOException {
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    private void loadTasks() {
        // Create a new ArrayList
        ArrayList<Class<? extends Task>> list = new ArrayList<>();

        // Get all classes from the bob.task package
        Set<Class<?>> allClasses = ClassGetter.getClassesFromPackage(Task.class.getPackageName());
        assert allClasses != null : "The set of all classes should not be null";

        // Iterate through the classes in the bob.task package
        for (Class<?> clazz : allClasses) {
            // If the class does not inherit bob.task.Task, ignore it
            if (!Task.class.isAssignableFrom(clazz) || clazz.equals(Task.class)) {
                continue;
            }

            // Add the class to the list
            list.add(clazz.asSubclass(Task.class));
        }

        // Initialize this.taskClasses and add all classes to it
        // At no point in the program is any Object added to taskClasses, except here.
        // Since we only add Class<? extends Task> into the array, it can be safely cast.
        @SuppressWarnings("unchecked")
        Class<? extends Task>[] a = (Class<? extends Task>[]) new Class[list.size()];
        this.taskClasses = a;
        list.toArray(this.taskClasses);
    }

    /**
     * Decodes the given string by calling the static {@code decode()} method on each task class known by this
     * Storage instance.
     *
     * @param encodedString the string to be decoded
     * @return the decoded Task instance
     * @throws IllegalArgumentException if the given string does not follow any of the formats
     */
    private Task decode(String encodedString) throws LineCorruptedException {
        assert encodedString != null : "Encoded string should not be null";
        for (Class<? extends Task> taskClass : taskClasses) {
            try {
                return (Task) taskClass.getMethod("decode", String.class).invoke(null, encodedString);
            } catch (NoSuchMethodException | IllegalAccessException ignored) {
            } catch (InvocationTargetException e) {
                Throwable cause = e.getCause();
                if (cause instanceof WrongTaskException) {
                    continue;
                } else if (cause instanceof LineCorruptedException) {
                    throw (LineCorruptedException) cause;
                }
            }
        }
        throw new LineCorruptedException();
    }

    /**
     * Encodes the given task.
     *
     * @param task the task to encode
     * @return the encoded string. Returns an empty string with linebreak the given task is not of a known class.
     */
    private String encode(Task task) {
        assert task != null : "task should not be null";
        return task.encode() + "\n";
    }

    /**
     * Reads data from the file at the file path of this Storage instance.
     *
     * @return a list of decoded tasks. Returns an empty list if the file does not exist
     * @throws FileCorruptedException if any line in the file does not follow the format
     */
    public List<Task> load() {
        if (!file.exists()) {
            return new ArrayList<>();
        }

        List<Task> tasks = new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (scanner.hasNext()) {
            try {
                tasks.add(decode(scanner.nextLine()));
            } catch (LineCorruptedException ignored) {
            } catch (RuntimeException e) {
                throw new FileCorruptedException();
            }
        }

        return tasks;
    }

    /**
     * Saves the given task list in the file at the file path of this storage instance.
     *
     * @param tasks the task list to be saved in the file
     * @throws IOException if the file cannot be opened
     */
    public void save(TaskList tasks) throws IOException {
        if (!file.exists()) {
            createFile();
        }

        FileWriter fw = new FileWriter(file, false);
        for (Task task : tasks) {
            fw.write(encode(task));
        }
        fw.close();
    }
}
