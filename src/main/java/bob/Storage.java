package bob;

import bob.exception.FileCorruptedException;
import bob.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Storage class handles storing and reading data from files.
 * The formats for encoding and decoding each task is: <br>
 * <ul>
 *  <li>todo: <code>T&lt;isDone&gt;&lt;desc&gt;</code></li>
 *  <li>deadline: <code>D&lt;isDone&gt;&lt;len(desc)#4&gt;&lt;desc&gt;&lt;by&gt;</code></li>
 *  <li>event: <code>E&lt;isDone&gt;&lt;len(desc)#4&gt;&lt;desc&gt;&lt;len(from)#4&gt;&lt;from&gt;&lt;to&gt;</code></li>
 * </ul>
 */
public class Storage {
    private final File file;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("ddMMuuuuHHmm");

    /**
     * Constructs a Storage instance that stores and reads files at the given file path.
     *
     * @param filePath where this instance stores and reads data from
     */
    public Storage(String filePath) {
        file = new File(filePath);
    }

    private void createFile() throws IOException {
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    /**
     * Decodes the given string.
     *
     * @param encodedString the string to be decoded
     * @return the decoded Task instance
     * @throws IllegalArgumentException if the given string does not follow any of the formats
     */
    private Task decode(String encodedString) {
        Task task;
        int n;
        String desc;

        switch(encodedString.charAt(0)) {
        case 'T':
            // T<isDone><desc>
            task = new Todo(encodedString.substring(2));
            break;
        case 'D':
            // D<isDone><len(desc)#4><desc><by>
            n = Integer.parseInt(encodedString.substring(2, 6));
            desc = encodedString.substring(6, 6 + n);
            String by = encodedString.substring(6 + n);
            LocalDateTime parsedBy = LocalDateTime.from(DATE_TIME_FORMATTER.parse(by));
            task = new Deadline(desc, parsedBy);
            break;
        case 'E':
            // E<isDone><len(desc)#4><desc><len(from)#4><from><to>
            n = Integer.parseInt(encodedString.substring(2, 6));
            int curr = 6 + n;
            desc = encodedString.substring(6, curr);
            n = Integer.parseInt(encodedString.substring(curr, curr + 4));

            curr += 4;
            String from = encodedString.substring(curr, curr + n);
            String to = encodedString.substring(curr + n);

            LocalDateTime parsedFrom = LocalDateTime.from(DATE_TIME_FORMATTER.parse(from));
            LocalDateTime parsedTo = LocalDateTime.from(DATE_TIME_FORMATTER.parse(to));

            task = new Event(desc, parsedFrom, parsedTo);
            break;
        default:
            throw new IllegalArgumentException();
        }

        if (encodedString.charAt(1) == '1') {
            task.mark();
        }

        return task;
    }

    /**
     * Encodes the given task.
     *
     * @param task the task to encode
     * @return the encoded string. Returns an empty string with linebreak the given task is not of a known class.
     */
    private String encode(Task task) {
        StringBuilder str = new StringBuilder();

        if (task instanceof Todo) {
            // todo: T<isDone><desc>
            str.append("T");
            str.append(task.getIsDone() ? 1 : 0);
            str.append(task.getDescription());
        } else if (task instanceof Deadline deadline) {
            // deadline: D<isDone><len(desc)#4><desc><by>
            str.append("D");
            str.append(task.getIsDone() ? 1 : 0);
            str.append(String.format("%04d", deadline.getDescription().length()));
            str.append(deadline.getDescription());

            String formattedBy = deadline.getBy().format(DATE_TIME_FORMATTER);
            str.append(formattedBy);
        } else if (task instanceof Event event) {
            // event: E<isDone><len(desc)#4><desc><len(from)#4><from><to>
            str.append("E");
            str.append(task.getIsDone() ? 1 : 0);
            str.append(String.format("%04d", event.getDescription().length()));
            str.append(event.getDescription());

            String formattedFrom = event.getFrom().format(DATE_TIME_FORMATTER);
            str.append(String.format("%04d", formattedFrom.length()));
            str.append(formattedFrom);

            String formattedTo = event.getTo().format(DATE_TIME_FORMATTER);
            str.append(formattedTo);
        }

        str.append('\n');
        return str.toString();
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
