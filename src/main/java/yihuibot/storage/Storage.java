package yihuibot.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.format.DateTimeFormatter;

import yihuibot.exception.taskformat.IncorrectTaskFormatException;

import yihuibot.task.TaskList;

/**
 * A storage class responsible for reading tasks from
 * a given file path by calling TaskReader.read,
 * and writing tasks into file by calling TaskWriter.write.
 *
 * @author Toh Yi Hui A0259080A
 */
public class Storage {
    private File file;
    private DateTimeFormatter formatter;

    /**
     * Instantiates a new Storage that can save or load data.
     *
     * @param filePath the path to read task data from.
     * @param dateTimeFormat the format pattern to use for date time.
     * @throws InvalidPathException when filePath cannot be converted to a Path.
     * @throws IllegalArgumentException when dateTimeFormat is not a valid pattern.
     * @throws IOException when an I/O error occurred.
     */
    public Storage(String filePath, String dateTimeFormat)
            throws InvalidPathException, IllegalArgumentException, IOException {
        Path path = Paths.get(filePath);
        try {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        } catch (FileAlreadyExistsException e) {

        }
        file = new File(filePath);
        formatter = DateTimeFormatter.ofPattern(dateTimeFormat);
    }

    /**
     * Reads the data in file and converts it into TaskList.
     *
     * @throws FileNotFoundException when the file is not found.
     * @throws IncorrectTaskFormatException when the file has corrupted data.
     * @return the TaskList.
     */
    public TaskList load() throws FileNotFoundException,
            IncorrectTaskFormatException {
        return new TaskReader(file, formatter).read();
    }

    /**
     * Saves the tasks into file in a human readable format.
     * This format can be read by the same Storage instance.
     *
     * @param tasks the data to save to file.
     * @throws IOException if the named file exists but is a director rather
     *                     than a regular file, does not exist but cannot be
     *                     created, or cannot be opened for any other reason.
     *                     (Paragraph taken from FileWriter javadocs Oracle)
     * @throws NullPointerException if the tasks is null.
     */
    public void save(TaskList tasks) throws IOException,
            NullPointerException {
        TaskWriter taskWriter = new TaskWriter(file, formatter, tasks);
        taskWriter.write();
        taskWriter.close();
    }
}
